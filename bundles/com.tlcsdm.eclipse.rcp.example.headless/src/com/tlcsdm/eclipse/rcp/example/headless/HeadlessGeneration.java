/*
 * Copyright (c) 2025 unknowIfGuestInDream
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *     * Neither the name of unknowIfGuestInDream, any associated website, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL UNKNOWIFGUESTINDREAM BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.tlcsdm.eclipse.rcp.example.headless;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

/**
 * This class controls all aspects of the application's execution
 */
public class HeadlessGeneration implements IApplication {

    /*
     * (non-Javadoc)
     * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.
     * IApplicationContext)
     */
    @Override
    public Object start(IApplicationContext context) throws Exception {
        boolean hasHelpOption = hasCommandLineOption(HeadlessGenerationConstants.COMMAND_LINE_OPTION_HELP);
        if (hasHelpOption) {
            printUsage();
            return IApplication.EXIT_OK;
        }

        boolean hasHelloOption = hasCommandLineOption(HeadlessGenerationConstants.COMMAND_LINE_OPTION_HELLO);
        if (hasHelloOption) {
            printMessage("Hello World!");
        }

        boolean hasScfgOption = hasCommandLineOption(HeadlessGenerationConstants.COMMAND_LINE_OPTION_OUTPUT);
        if (hasScfgOption) {
            String scfgOptionValue = getCommandLineOption(HeadlessGenerationConstants.COMMAND_LINE_OPTION_OUTPUT);
            if (scfgOptionValue == null) {
                printMessage("File spcecified by \"-output\" option do not exist.");
                return IApplication.EXIT_OK;
            }

            IPath scfgFile = new Path(scfgOptionValue);
            if (scfgFile.toFile().exists()) {
                printMessage("File spcecified by \"-output\" option exists!.");
                return IApplication.EXIT_OK;
            } else {
                File file = scfgFile.toFile();
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                if (file.isDirectory()) {
                    file.mkdir();
                    printMessage("File spcecified by \"-output\" option created!.");
                    return IApplication.EXIT_OK;
                } else {
                    try {
                        file.createNewFile();
                        printMessage("File spcecified by \"-output\" option created!.");
                    } catch (IOException e) {
                        printMessage("File spcecified by \"-output\" option create failed!.");
                        return IApplication.EXIT_OK;
                    }
                }
            }
        }

        return IApplication.EXIT_OK;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.equinox.app.IApplication#stop()
     */
    @Override
    public void stop() {

    }

    private boolean hasCommandLineOption(String option) {
        boolean hasOption = false;
        String[] args = Platform.getCommandLineArgs();
        for (String arg : args) {
            if (arg.equals(option)) {
                hasOption = true;
                break;
            }
        }

        return hasOption;
    }

    private String getCommandLineOption(String option) {
        String argument = null;

        String[] args = Platform.getCommandLineArgs();
        for (int index = 0; index < args.length; index++) {
            if (args[index].equals(option)) {
                if ((index + 1) <= args.length) {
                    argument = args[index + 1];
                }
                break;
            }
        }

        return argument;
    }

    private void printUsage() {
        System.out.println("Usage:");

        printHelp(HeadlessGenerationConstants.COMMAND_LINE_OPTION_HELP, "Show help information");
        printHelp(
                HeadlessGenerationConstants.COMMAND_LINE_OPTION_APPLICATION
                        + "=\"<com.tlcsdm.eclipse.rcp.example.headless.headlessGeneration>\"",
                "Use com.tlcsdm.eclipse.rcp.example.headless.headlessGeneration application");
        printHelp(HeadlessGenerationConstants.COMMAND_LINE_OPTION_OUTPUT + "=\"<output file path>\"",
                "file path to generate");
        printHelp(HeadlessGenerationConstants.COMMAND_LINE_OPTION_HELLO, "Print hello world");
    }

    private void printHelp(String command, String description) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(command);
        for (int i = 0; i < 60 - command.length(); i++) {
            buffer.append(' ');
        }
        buffer.append(description);

        System.out.println(buffer.toString());
    }

    private void printMessage(String message) {
        System.out.println(message);
    }
}

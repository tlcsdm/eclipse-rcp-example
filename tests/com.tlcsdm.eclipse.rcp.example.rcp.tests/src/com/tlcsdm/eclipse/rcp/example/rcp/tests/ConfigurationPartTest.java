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
package com.tlcsdm.eclipse.rcp.example.rcp.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotLabel;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ConfigurationPartTest {

    private static SWTWorkbenchBot bot;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        bot = new SWTWorkbenchBot();
        // 关闭欢迎页（如有）
        try {
            bot.viewByTitle("Welcome").close();
        } catch (Exception e) {
            // ignore
        }

        // 打开 ViewPart，如果你已注册为视图
        // bot.menu("Window").menu("Show View").click();
        // bot.tree().expandNode("Your Category").select("Configuration View"); //
        // 修改为实际分类
        // bot.button("Open").click();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        bot.resetWorkbench();
    }

    @Test
    public void testLabelAndButtonInteraction() {
        System.out.println("开始测试 ConfigurationPart 的标签和按钮交互...");

        SWTBotView view = bot.viewByTitle("Eclipse Example Configuration");
        SWTBotLabel label = view.bot().label(0);
        SWTBotButton button = view.bot().button("Click Me");

        System.out.println(label.getText());
        assertEquals("Hello, Eclipse RCP!", label.getText());

        button.click();

        assertEquals("You clicked the button!", label.getText());
    }
}

# Eclipse RCP Example

This repository is a sample project built with **Eclipse RCP + Tycho + Maven**. It shows how to build, package, and distribute an Eclipse Rich Client application.

If you want a quick summary of what this project is, think of it as:

- a runnable **Eclipse RCP desktop application example**
- a separately buildable **headless command-line application example**
- a complete project skeleton with **welcome page / ViewPart / update site / SWTBot tests**

## What is included

### 1. RCP desktop application
The main application is located in `bundles/com.tlcsdm.eclipse.rcp.example.rcp` and includes:

- Eclipse RCP product definitions
- a custom perspective
- a sample view: `Eclipse Example Configuration`
- a simple interactive UI (label + button)

### 2. Headless application
`bundles/com.tlcsdm.eclipse.rcp.example.headless` provides a command-line example without a UI. It demonstrates:

- command-line argument handling (for example `-help`, `-hello`, and `-output`)
- independently executed tasks such as batch or generation jobs started from the command line
- file output capabilities that can create output files or directories from arguments

### 3. Intro / Welcome page
`bundles/com.tlcsdm.eclipse.rcp.example.intro` contains the welcome page configuration and can be used as a reference for customizing the product home page.

### 4. Update Site
The repository also includes update-related modules and update site packaging, which can be used as a reference for publishing and upgrading an Eclipse application.

### 5. Automated testing
`tests/com.tlcsdm.eclipse.rcp.example.rcp.tests` contains SWTBot UI tests that verify the basic interaction in the sample view.

## Repository structure

- `bundles/`: core plug-ins and application modules
- `features/`: Eclipse feature definitions
- `sites/`: RCP product and update site packaging modules
- `tests/`: SWTBot test modules

## Typical use cases

This repository is useful if you want to:

- quickly start an Eclipse RCP project skeleton
- learn how a Tycho multi-module project is organized
- maintain a GUI application, a headless application, and an update site in the same repository
- reference Eclipse plug-in productization and automated UI testing examples

## Requirements

This project is built with [Tycho](https://github.com/eclipse-tycho/tycho) and [Maven](https://maven.apache.org/).

- **JDK 21+**
- **Maven 3.9.0 or newer**
- It is recommended to run `java -version` before building to confirm that the environment is using JDK 21.

## Build

Dev build:

```bash
mvn clean verify
```

Release build:

```bash
mvn clean org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=2.0.0 verify
```

## Install

Download from [Jenkins](https://jenkins.tlcsdm.com/job/eclipse-rcp/job/eclipse-rcp-example)

# Eclipse RCP Example

一个基于 **Eclipse RCP + Tycho + Maven** 的示例仓库，用来演示如何构建、打包和分发一个 Eclipse 富客户端应用。

如果你想快速判断这个项目是什么，可以把它理解为：

- 一个可运行的 **Eclipse RCP 桌面应用示例**
- 一个可单独构建的 **Headless 命令行应用示例**
- 一个包含 **欢迎页 / ViewPart / 更新站点 / SWTBot 测试** 的完整工程骨架

## 当前项目包含什么

### 1. RCP 桌面应用
主应用位于 `bundles/com.tlcsdm.eclipse.rcp.example.rcp`，包含以下内容：

- Eclipse RCP 产品定义
- 自定义 Perspective
- 示例 View：`Eclipse Example Configuration`
- 简单交互界面（标签 + 按钮）

### 2. Headless 应用
`bundles/com.tlcsdm.eclipse.rcp.example.headless` 提供无界面的命令行应用示例，可用于演示以下能力：

- 命令行参数处理（例如 `-help`、`-hello`、`-output`）
- 独立执行任务（例如命令行触发的批处理或生成任务）
- 文件输出能力（可按参数创建输出文件或目录）

### 3. Intro / Welcome 页面
`bundles/com.tlcsdm.eclipse.rcp.example.intro` 提供欢迎页相关配置，可作为 Eclipse 产品首页定制示例。

### 4. Update Site
仓库同时包含更新功能与 update site 相关模块，可用于演示 Eclipse 应用的发布与升级流程。

### 5. 自动化测试
`tests/com.tlcsdm.eclipse.rcp.example.rcp.tests` 中提供 SWTBot UI 测试，用于验证示例 View 的基本交互。

## 仓库结构

- `bundles/`：核心插件与业务模块
- `features/`：Eclipse feature 定义
- `sites/`：RCP product 与 update site 打包模块
- `tests/`：SWTBot 测试模块

## 适合什么场景

这个仓库适合你在以下场景中使用：

- 想快速搭建一个 Eclipse RCP 工程骨架
- 想了解 Tycho 多模块工程如何组织
- 想同时维护 GUI 应用、Headless 应用和 Update Site
- 想参考 Eclipse 插件产品化与自动化测试示例

## 构建要求 / Requirements

本项目使用 [Tycho](https://github.com/eclipse-tycho/tycho) 和 [Maven](https://maven.apache.org/) 构建。

- **JDK 21+**
- **Maven 3.9.0+**
- 构建前建议先执行 `java -version`，确认当前环境已切换到 JDK 21

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

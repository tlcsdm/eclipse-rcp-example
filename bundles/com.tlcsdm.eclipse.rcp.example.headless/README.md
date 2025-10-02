## Use

### Application中集成

1. Print hello world

```
"Eclipse RCP Example Headlessc.exe" -nosplash -application com.tlcsdm.eclipse.rcp.example.headless.headlessGeneration -hello
```

2. Help

```
"Eclipse RCP Example Headlessc.exe" -nosplash -application com.tlcsdm.eclipse.rcp.example.headless.headlessGeneration -help
```
3. Output  
generate a file.

```
"Eclipse RCP Example Headlessc.exe" -nosplash -application com.tlcsdm.eclipse.rcp.example.headless.headlessGeneration -output "D:\\helloHeadless"
```

### 使用EASE

引入 com.tlcsdm.eclipse.rcp.example.headless.ease.feature


#### test.py
```
"Eclipse RCP Example Headlessc.exe" -nosplash -application org.eclipse.ease.runScript -script "test.py"
```

```python
print("=== Py4J Python Script Test ===")
```

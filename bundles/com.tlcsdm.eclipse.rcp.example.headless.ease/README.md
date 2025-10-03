## Use

```
"Eclipse RCP Example Headlessc.exe" -nosplash -application org.eclipse.ease.runScript -script "hello.py"
```

hello.py

```python
loadModule("HelloModule")

print(hello("Python"))
print(add(10, 20))
```
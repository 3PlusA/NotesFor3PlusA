# php语言特性bugs
- [php语言特性bugs](#php语言特性bugs)
  - [1.Exception类](#1exception类)
    - [1.1 构造函数执行命令](#11-构造函数执行命令)
## 1.Exception类
### 1.1 构造函数执行命令
例子：
```php
if(isset($_GET['v1']) && isset($_GET['v2'])){
    $v1 = $_GET['v1'];
    $v2 = $_GET['v2'];

    if(preg_match('/[a-zA-Z]+/', $v1) && preg_match('/[a-zA-Z]+/', $v2)){
            eval("echo new $v1($v2());");
    }

}
?>
```

v1传Exception,v2传命令
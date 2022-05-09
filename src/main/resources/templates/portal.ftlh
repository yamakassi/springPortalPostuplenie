<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Поступление</title>
</head>
<body>
<h1>Поступай <a href="/login">Войти</a></h1>
<hr>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="выход"/>
</form>
<h5>Направления</h5>
<#list institute as inst>
    <div>
        <p>${inst.title}</p>
        <span>${inst.description}
        </span>
        <a href="/institute/${inst.abbr}">об</a>
    </div>
</#list>
<h3>Добавить институт</h3>
<form action="/institute/create" method="post" enctype="multipart/form-data">
    Название института : <input type="text" name="title">
    Сокращение института : <input type="text" name="abbr">
    Описание : <input type="text" name="description">
    Директор института : <input type="text" name="director">
    Контакты звание института : <input type="text" name="contacts">
    Фото: <input type="file" name="file">
    <input type="submit" value="добавить">
</form>
<!--
 private String name;
    private String description;
    private String director;
    private String contacts;
    private List<String> directions;
-->
</body>
</html>
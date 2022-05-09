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
<h1>Об институте</h1>
<hr>
<h5>Информация</h5>
<hr>
<b>Название института</b>${institute.title}<br>
<img src="/images/${institute.image.id}" height="70px" >
<b>Описание института</b>${institute.description}<br>
<b>Директор института</b>${institute.director}<br>
<b>Контакты института</b>${institute.contacts}<br>
<b>Направления института</b>


<form action="/institute/delete/${institute.getAbbr()}" method="post" >

    <input type="submit" value="удалить">
</form>

</body>
</html>
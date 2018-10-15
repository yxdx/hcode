<%--
  Created by IntelliJ IDEA.
  User: MY
  Date: 2018/10/5
  Time: 3:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>何码[hcode]-程序员技术博客垂直搜索</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="icon" href="hcode_icon.png">
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-sm-4 col-sm-offset-4 col-xs-8 col-xs-offset-2" style="padding-top: 160px">
			<img src="hcode_logo.png" class="img-responsive" alt="hcode" title="hcode">
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6 col-sm-offset-3" style="padding-top: 50px">
			<form role="form" action="searchAction_search" method="get">
				<div class="input-group" style="display: none">
					<input type="text" class="form-control" name="page" value="1">
				</div>
				<div class="input-group"  style="display: none">
					<input type="text" class="form-control" name="searchMethod" value="default">
				</div>
				<div class="input-group">
					<input type="text" class="form-control" name="keywords" placeholder="Search for blogs..."/>
					<span class="input-group-btn"><button class="btn btn-default" type="submit">Search</button></span>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>
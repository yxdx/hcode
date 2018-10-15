<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: MY
  Date: 2018/10/7
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>Search for blogs...</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="icon" href="hcode_icon.png">
</head>
<body>
<div class="container">
	<!--搜索框-->
	<div class="row">
		<div class="col-sm-4 col-sm-offset-4 col-xs-8 col-xs-offset-2" style="padding-top: 30px">
			<a href="index.jsp"><img src="hcode_logo.png" class="img-responsive" alt="hcode"></a>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-8 col-sm-offset-2" style="padding-top: 30px;padding-bottom: 20px">
			<form role="form" action="#">
				<div class="input-group" style="display: none">
					<input type="text" class="form-control" name="page" value="1">
				</div>
				<div class="input-group" style="display: none">
					<input type="text" class="form-control" name="searchMethod" value="default">
				</div>
				<div class="input-group">
					<input id="keywords" type="text" class="form-control" name="keywords"
					       placeholder="Search for blogs..."
					       value="<s:property value="#parameters.keywords"/>"/>
					<span class="input-group-btn"><button class="btn btn-default" type="submit">Search</button></span>
				</div>
			</form>
		</div>
	</div>
	<!--标签页-->
	<div style="padding-bottom: 20px">
		<ul class="nav nav-tabs">
			<li id="m_default" role="presentation"><a
					href="searchAction_search?searchMethod=default&keywords=${param.keywords}&page=1">默认</a></li>
			<li id="m_newest" role="presentation"><a
					href="searchAction_search?searchMethod=newest&keywords=${param.keywords}&page=1">最新</a></li>
			<li id="m_hottest" role="presentation"><a
					href="searchAction_search?searchMethod=hottest&keywords=${param.keywords}&page=1">最热</a></li>
		</ul>
	</div>
	<!--结果展示-->
	<div class="lead">共搜索到
		<small class="text-info"><s:property value="#session.numFound"/></small>
		条记录
	</div>

	<s:iterator value="#session.blogs" var="blog">
		<div class="row">
			<div class="col-xs-12">
				<div class="thumbnail">
					<div class="caption">
						<a href="<s:property value="#blog.url"/>"><h4><s:property value="#blog.title"
						                                                          escape="false"/></h4></a>
						<div class="row">
							<div class="col-sm-4 col-xs-12"><a
									href="<s:property value="#blog.authorUrl"/>">作者:<s:property
									value="#blog.authorName"/></a></div>
							<div class="col-sm-4 col-xs-6 text-info">发布日期:<s:property value="#blog.postdate"/></div>
							<div class="col-sm-4 col-xs-6 text-info">阅读量:<s:property value="#blog.pageviews"/></div>
						</div>
						<div>
							<p class="text-justify summary"><s:property value="#blog.summary"/></p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:iterator>
	<!--分页-->
	<% int pageint = Integer.parseInt(request.getParameter("page"));
		if (pageint <= 3) {%>
	<nav class="text-center">
		<ul class="pagination">
			<li class="disabled">
				<a href="" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
				</a>
			</li>
			<li><a href="searchAction_search?searchMethod=${param.searchMethod}&keywords=${param.keywords}&page=1">1</a>
			</li>
			<li><a href="searchAction_search?searchMethod=${param.searchMethod}&keywords=${param.keywords}&page=2">2</a>
			</li>
			<li><a href="searchAction_search?searchMethod=${param.searchMethod}&keywords=${param.keywords}&page=3">3</a>
			</li>
			<li><a href="searchAction_search?searchMethod=${param.searchMethod}&keywords=${param.keywords}&page=4">4</a>
			</li>
			<li><a href="searchAction_search?searchMethod=${param.searchMethod}&keywords=${param.keywords}&page=5">5</a>
			</li>
			<li><a href="searchAction_search?searchMethod=${param.searchMethod}&keywords=${param.keywords}&page=6">6</a>
			</li>
			<li>
				<a href="searchAction_search?searchMethod=${param.searchMethod}&keywords=${param.keywords}&page=${param.page + 1}"
				   aria-label="Next">
					<span aria-hidden="true">&raquo;</span>
				</a>
			</li>
		</ul>
	</nav>
	<% } else {%>
	<nav class="text-center">
		<ul class="pagination">
			<li>
				<a href="searchAction_search?searchMethod=${param.searchMethod}&keywords=${param.keywords}&page=${param.page - 1}"
				   aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
				</a>
			</li>
			<li>
				<a href="searchAction_search?searchMethod=${param.searchMethod}&keywords=${param.keywords}&page=${param.page - 2}">${param.page -2}</a>
			</li>
			<li>
				<a href="searchAction_search?searchMethod=${param.searchMethod}&keywords=${param.keywords}&page=${param.page - 1}">${param.page - 1}</a>
			</li>
			<li>
				<a href="searchAction_search?searchMethod=${param.searchMethod}&keywords=${param.keywords}&page=${param.page}">${param.page}</a>
			</li>
			<li>
				<a href="searchAction_search?searchMethod=${param.searchMethod}&keywords=${param.keywords}&page=${param.page + 1}">${param.page + 1}</a>
			</li>
			<li>
				<a href="searchAction_search?searchMethod=${param.searchMethod}&keywords=${param.keywords}&page=${param.page + 2}">${param.page + 2}</a>
			</li>
			<li>
				<a href="searchAction_search?searchMethod=${param.searchMethod}&keywords=${param.keywords}&page=${param.page + 2}">${param.page + 3}</a>
			</li>
			<li>
				<a href="searchAction_search?searchMethod=${param.searchMethod}&keywords=${param.keywords}&page=${param.page + 1}"
				   aria-label="Next">
					<span aria-hidden="true">&raquo;</span>
				</a>
			</li>
		</ul>
	</nav>
	<% }%>

</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script>
	$(function () {
		var searchMethod = "${param.searchMethod}";
		if (searchMethod == "default") {
			$("#m_default").addClass("active");
		} else if (searchMethod == "hottest") {
			$("#m_hottest").addClass("active");
		} else {
			$("#m_newest").addClass("active");
		}


		$(".pagination").children().each(function () {
			if (this.innerText == ${param.page}) {
				$(this).addClass("active");
			}

		});

	})
</script>
</body>
</html>

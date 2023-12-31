<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<head>
<title>Curso JSP - JDev Treinamentos</title>

<!-- Para se pegar as informacoes do CSS da pagina, temos que criar um caminho PADRAO
para conseguir pegar as informacoes. Se analisar o codigo, vai ver que a pasta "<%= request.getContextPath() %>/assets"
fica fora da pasta "principal", que e onde as paginas HTML sao acessadas, e devido a isso
da erro na hora de carregar o CSS. Para isso teremos que utilizar o CONTEXTPATH do JSP -->

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description" content="Projetos de estudo feito em JSP" />
<meta name="author" content="Valdir Barbosa da Silva Junior" />
<!-- Favicon icon -->
<link rel="icon" href="<%= request.getContextPath() %>/assets/images/favicon.ico" type="image/x-icon">
<!-- Google font-->
<link href="https://fonts.googleapis.com/css?family=Roboto:400,500"
	rel="stylesheet">
<!-- waves.css -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/pages/waves/css/waves.min.css"
	type="text/css" media="all">
<!-- Required Fremwork -->
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/assets/css/bootstrap/css/bootstrap.min.css">
<!-- waves.css -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/pages/waves/css/waves.min.css"
	type="text/css" media="all">
<!-- themify icon -->
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/assets/icon/themify-icons/themify-icons.css">
<!-- Font Awesome -->
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/assets/icon/font-awesome/css/font-awesome.min.css">
<!-- scrollbar.css -->
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/assets/css/jquery.mCustomScrollbar.css">
<!-- am chart export.css -->
<link rel="stylesheet"
	href="https://www.amcharts.com/lib/3/plugins/export/export.css"
	type="text/css" media="all" />
<!-- Style.css -->
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/style.css">
</head>

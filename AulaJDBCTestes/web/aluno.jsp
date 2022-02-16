<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css"
    href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.js"></script>
<script type="text/javascript" src="http://www.godtur.no/godtur/js/jquery-ui-1.8.18.custom.min.js"></script>
<title>Adicionar Aluno</title>
</head>
<body>
    <script>
        $(function() {
            $('input[name=dob]').datepicker();
        });
    </script>

    <form method="POST" action='AlunoController' name="frmAddUser">
        Matricula : <input type="text" readonly="readonly" name="matricula"
            value="<c:out value="${aluno.matricula}" />" /> <br /> 
        Nome : <input
            type="text" name="nome"
            value="<c:out value="${aluno.nome}" />" /> <br /> 
        Data de Nascimento : <input
            type="text" placeholder="dd/MM/yyyy" name="nasc" data-date-format="dd/MM/yyyy"
            value="<fmt:formatDate pattern="dd/MM/yyyy" value="${aluno.nascimento}" />" /> <br /> 
        Endereco : <input type="text" name="endereco"
            value="<c:out value="${aluno.endereco}" />" /> <br /> <input
            type="submit" value="Submit" />
    </form>
</body>
</html>
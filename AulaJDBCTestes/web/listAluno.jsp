<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar Alunos</title>
    </head>
<body>
    <table border=1>
        <thead>
            <tr>
                <th>Matricula</th>
                <th>Nome</th>
                <th>Data de Nascimento</th>
                <th>Endereço</th>
                <th colspan=2>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${alunos}" var="aluno">
                <tr>
                    <td><c:out value="${aluno.matricula}" /></td>
                    <td><c:out value="${aluno.nome}" /></td>
                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${aluno.nascimento}" /></td>
                    <td><c:out value="${aluno.endereco}" /></td>
                    <td><a href="UserController?action=edit&matricula=<c:out value="${aluno.matricula}"/>">Atualizar</a></td>
                    <td><a href="UserController?action=delete&matricula=<c:out value="${aluno.matricula}"/>">Apagar</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="AlunoController?action=insert">Adicionar Aluno</a></p>
</body>
</html>
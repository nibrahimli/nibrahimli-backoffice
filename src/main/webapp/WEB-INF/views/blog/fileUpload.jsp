<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
    <head>
        <title>Upload a file please</title>
	<%-- <link rel="stylesheet" href="<spring:theme code='style'/>" type="text/css"/> --%>
    </head>
    <body>
        <h1>Please upload a file</h1>
        <form method="post" action="<c:url value="/blog/fileUpload" />" enctype="multipart/form-data">
            <input type="text" name="name"/>
            <input type="file" name="file"/>
            <input type="submit"/>
        </form>
    </body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Create questionnaire</title>
</head>

<body>
<fieldset class="buttons">
    <a href="/ConnectedHealth/questionnaires">Back to questionnaire list</a>
</fieldset>

<div id="create-question" class="content scaffold-create" role="main">
    <h1>Create Questionnaire</h1>

    <g:render template="/questionnaire/form" />
</div>
</body>
</html>
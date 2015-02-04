<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Create questionnaire</title>
</head>

<body>
<div class="questionnaireSection">
    <div class="nav" role="navigation">
        <fieldset class="buttons buttonsBlue">
            <a href="/ConnectedHealth/questionnaires" class="list">Questionnaire list</a>
        </fieldset>
    </div>

<div id="create-question" class="content scaffold-create" role="main">
    <h1>Create Questionnaire</h1>

    <g:render template="/questionnaire/form" />
</div>
    </div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Edit question</title>
</head>

<body>
<div class="questionnaireSection">

    <div class="nav" role="navigation">
        <fieldset class=" buttonsBlue">
            <a href="/ConnectedHealth/questionnaires/${questionnaire.id}/show" class="list">Back to Questionnaire</a>
        </fieldset>
    </div>

    <div id="create-medicalNote" class="content scaffold-create" role="main">
        <h1>Edit question</h1>

        <g:render template="/questions/form" locals="${[questionnaire: questionnaire]}"/>
    </div>
</div>
</body>
</html>
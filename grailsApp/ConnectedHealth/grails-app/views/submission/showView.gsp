<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${submission.questionnaire.name + " Submission"}</title>
</head>

<body>
<div class="questionnaireSection">
<div class="nav" role="navigation">
    <fieldset class="buttons buttonsBlue">
        <a href="/ConnectedHealth/patients/${patient.id}/show" class="list">Patient Profile</a>
        <a href="/ConnectedHealth/patients/${patient.id}/submissions" class="list">Submission List</a>
    </fieldset>
</div>

<div id="list-questionnaire" class="content scaffold-list" role="main">
    <h1>${submission.questionnaire.name + " Submission"}</h1>

    <ol class="property-list questionnaire">
        <li class="fieldcontain">
            <span class="property-label">Date</span>
            <span class="property-value" aria-labelledby="patient-label">${views.ViewHelpers.formatDate(submission.created)}</span>
        </li>
    </ol>

    <table style="table-layout: fixed; width: 90%" class="table table-bordered tablePaddings">
        <thead>
        <tr>
            <th>Question</th>
            <th>Answer Format</th>
            <th>Answer</th>
        </tr>
        </thead>
        <tbody>

        <g:each in="${submission.singleSelectionAnswers}">
            <tr>
                <td style="width: 10%">
                    ${it.question.content}
                </td>
                <td>
                    ${views.ViewHelpers.answerFormatString(it.question.answerFormat)}
                </td>
                <td style="width: 90%">
                    ${it.choice.content}
                </td>
            </tr>
        </g:each>

        <g:each in="${submission.multipleSelectionAnswers}">
            <tr>
                <td style="width: 10%">
                    ${it.question.content}
                </td>
                <td>
                    ${views.ViewHelpers.answerFormatString(it.question.answerFormat)}
                </td>
                <td style="width: 90%">
                    <g:each in="${it.choices}">
                        ${it.content}<br />
                    </g:each>
                </td>
            </tr>
        </g:each>

        <g:each in="${submission.textAnswers}">
            <tr>
                <td style="width: 10%">
                    ${it.question.content}
                </td>
                <td>
                    ${views.ViewHelpers.answerFormatString(it.question.answerFormat)}
                </td>
                <td style="width: 90%">
                    ${it.answer}
                </td>
            </tr>
        </g:each>

        </tbody>
    </table>

</div>
    </div>
</body>
</html>
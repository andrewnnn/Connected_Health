<g:if test="${question != null}">
    <% FORM_URL = "/ConnectedHealth/questionnaires/${questionnaire.id}/questions/${question.id}" %>
    <% FORM_METHOD = "PUT" %>
</g:if>
<g:else>
    <% FORM_URL = "/ConnectedHealth/questionnaires/${questionnaire.id}/questions/create" %>
    <% FORM_METHOD = "POST" %>
</g:else>

<% answerFormatValues = [0, 1, 2] %>
<% answerFormatNames = ["Multiple choice - single selection", "Multiple choice - multiple selection", "Text"] %>

<g:form url="${FORM_URL}" method="${FORM_METHOD}">
    <fieldset class="form">
        <div class="fieldcontain  required">
            <label for="content">
                Content
                <span class="required-indicator">*</span>
            </label>
            <g:if test="${question != null}">
                <g:textArea name="content" id="content" rows="5" cols="40" value="${question.content}"/>
            </g:if>
            <g:else>
                <g:textArea name="content" id="content" rows="5" cols="40"/>
            </g:else>

            <br/>
            <br/>

            <label for="answerFormat">
                Answer Format
                <span class="required-indicator">*</span>
            </label>
            <g:if test="${question != null}">
                <g:select name="answerFormat" from="${answerFormatNames}" keys="${answerFormatValues}"
                          value="${question.answerFormat}"/>
            </g:if>
            <g:else>
                <g:select name="answerFormat" from="${answerFormatNames}" keys="${answerFormatValues}"/>
            </g:else>

            <g:hiddenField name="questionnaireID" value="${questionnaire.id}"/>
        </div>
    </fieldset>
    <br/>
    <g:if test="${question != null}">
        <input type="submit" name="update" class="save btn btn-primary bootButtons" value="Update" id="update">
    </g:if>
    <g:else>
        <input type="submit" name="create" class="save btn btn-primary bootButtons" value="Create" id="create">
    </g:else>
</g:form>
<g:if test="${questionnaire != null}">
    <% FORM_URL = "/ConnectedHealth/questionnaires/${questionnaire.id}" %>
    <% FORM_METHOD = "PUT" %>
</g:if>
<g:else>
    <% FORM_URL = "/ConnectedHealth/questionnaires/create" %>
    <% FORM_METHOD = "POST" %>
</g:else>

<g:form url="${FORM_URL}" method="${FORM_METHOD}">
    <fieldset class="form">
        <div class="fieldcontain  required">
            <label for="content">
                Name
                <span class="required-indicator">*</span>
            </label>
            <g:if test="${questionnaire != null}">
                <g:textField name="name" id="content" value="${questionnaire.name}"/>
            </g:if>
            <g:else>
                <g:textField name="name" id="content"/>
            </g:else>

            <br />
            <br />

            <label for="description">
                Description
                <span class="required-indicator">*</span>
            </label>
            <g:if test="${questionnaire != null}">
                <g:textArea name="description" id="description" rows="5" cols="40" value="${questionnaire.description}"/>
            </g:if>
            <g:else>
                <g:textArea name="description" id="description" rows="5" cols="40"/>
            </g:else>
        </div>
    </fieldset>
        <br/>
        <g:if test="${questionnaire != null}">
            <input type="submit" name="update" class="save btn btn-danger bootButtons" value="Update" id="update">
            <input type="button" onclick="window.location='/ConnectedHealth/questionnaires/${questionnaire.id}/show'" class="save btn btn-default bootButtons" value="Cancel">
        </g:if>
        <g:else>
            <input type="submit" name="create" class="save btn btn-danger bootButtons" value="Create" id="create">
            <input type="button" onclick="window.location='/ConnectedHealth/questionnaires'" class="save btn btn-default bootButtons" value="Cancel">
        </g:else>

</g:form>
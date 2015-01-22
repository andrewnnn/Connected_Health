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
                <g:textField name="content" id="content" value="${questionnaire.name}"/>
            </g:if>
            <g:else>
                <g:textField name="content" id="content"/>
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

    <fieldset class="buttons">
        <g:if test="${questionnaire != null}">
            <input type="submit" name="update" class="save" value="Update" id="update">
        </g:if>
        <g:else>
            <input type="submit" name="create" class="save" value="Create" id="create">
        </g:else>
    </fieldset>
</g:form>
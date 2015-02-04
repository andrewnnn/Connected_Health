<g:if test="${medicalNote != null}">
    <% FORM_URL = "/ConnectedHealth/patients/${patient.id}/medicalnotes/${medicalNote.id}" %>
    <% FORM_METHOD = "PUT" %>
</g:if>
<g:else>
    <% FORM_URL = "/ConnectedHealth/patients/${patient.id}/medicalnotes/create" %>
    <% FORM_METHOD = "POST" %>
</g:else>

<g:form url="${FORM_URL}" method="${FORM_METHOD}">
    <fieldset class="form">
        <div class="fieldcontain  required">
            <label for="content">
                Content
                <span class="required-indicator">*</span>
            </label>
            <g:if test="${medicalNote != null}">
                <g:textArea name="content" id="content" rows="5" cols="40" value="${medicalNote.content}"/>
            </g:if>
            <g:else>
                <g:textArea name="content" id="content" rows="5" cols="40"/>
            </g:else>
        </div>
        <g:hiddenField name="patientID" value="${patient.id}" />
    </fieldset>

        <br/>
        <g:if test="${medicalNote != null}">
            <input type="submit" name="update" class="save btn btn-danger bootButtons" value="Update" id="update">
        </g:if>
        <g:else>
            <input type="submit" name="create" class="save btn btn-danger bootButtons" value="Create" id="create">
        </g:else>

</g:form>
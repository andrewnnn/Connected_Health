
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>New Profile</title>
</head>

<body>

<div class="patientSection">
<div id="create-medicalNote" class="content scaffold-create" role="main">
    <div class="nav" role="navigation">
        <fieldset class="buttons buttonsRed">
            <a href="/ConnectedHealth/patients" class="list">Back to patient list</a>
        </fieldset>
    </div>

    <h1>Create a new Profile</h1>

    <g:form url="/ConnectedHealth/patients/create" method="POST">

        <fieldset class="form">
            <div class="fieldcontain  required">



                <label for="contactEmail">
                    Contact Email
                    <span class="required-indicator">*</span>
                </label>
                <g:textField name="contactEmail" value="" id="contactEmail" size="25"/>

                <br/>
                <br/>

                <label for="firstName">
                    First Name
                    <span class="required-indicator">*</span>
                </label>
                <g:textField name="firstName" value="" id="firstName" size="10"/>

                <br/>
                <br/>

                <label for="lastName">
                    Last Name
                    <span class="required-indicator">*</span>
                </label>
                <g:textField name="lastName" value="" id="lastName" size="10"/>

                <br/>
                <br/>

                <label for="homeAddress">
                    Home Address
                    <span class="required-indicator">*</span>
                </label>
                <g:textField name="homeAddress" value="" id="homeAddress" size="50"/>

                <br/>
                <br/>

                <label for="phone">
                    Phone
                    <span class="required-indicator">*</span>
                </label>
                <g:textField name="phone" value="" id="phone" rows="5"  size="10"/>

                <br/>
                <br/>

                <input type="submit" name="create" class="save btn btn-danger bootButtons" value="Create" id="create">


            </div>
        </fieldset>

    </g:form>
</div>
    </div>
</body>
</html>
<!-- JSTL TAGS -->
<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!-- SPRING FORM TAG -->
<%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0"
    />
    <title>Add todo</title>
  </head>
  <body>
    <h2>Add Todo</h2>
    <!-- form: Mapping this form to Todo CLass Beans, allowing
    controller to access todo NB: Means every controller returning this page should have todo instance on medel -->
    <form:form method="post" modelAttribute="todo">
      <div>
        <form:label path="description"
          >Description</form:label
        >
        <form:input
          type="text"
          placeholder="Description..."
          path="description"
        />
        <form:errors path="description" />
      </div>

      <div>
        <!-- // Like value in react tied to state -->
        <form:input type="hidden" path="id" />
      </div>
      <div>
        <form:input type="hidden" path="done" />
      </div>

      <div>
        <form:label path="targetDate"
          >Target Date</form:label
        >
        <form:input type="text" path="targetDate" />
        <form:errors path="targetDate" />
      </div>
      <button type="submit">Submit</button>
    </form:form>
  </body>
</html>

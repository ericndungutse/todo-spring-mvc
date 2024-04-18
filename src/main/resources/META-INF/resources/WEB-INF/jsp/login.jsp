<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0"
    />
    <title>Todo | Login</title>
  </head>
  <body>
    <h3>
      <span
        style="
          background-color: #f4f4f4;
          display: inline-block;
          padding: 4px;
          text-align: center;
          border-radius: 5px;
          color: #1f1d1d;
        "
        >Loggin</span
      >
    </h3>

    <form method="post">
      <c:if test="${errorMessage != null }">
        <p
          style="
            background-color: rgba(255, 0, 0, 0.178);
            color: red;
            padding: 10px;
          "
        >
          ${errorMessage}
        </p>
      </c:if>

      <div>
        <label for="username">Username</label>
        <input type="text" name="username" id="username" />
      </div>
      <div>
        <label for="password">Password</label>
        <input
          type="password"
          name="password"
          id="password"
        />
      </div>
      <button type="submit">Login</button>
    </form>
  </body>
</html>

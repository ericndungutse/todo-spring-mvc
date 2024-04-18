<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0"
    />
    <title>Document</title>
    <link
      href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css"
      rel="stylesheet"
    />
  </head>
  <body class="px-64 py-4">
    <h1 class="text-green-500">Todos by: ${username}</h1>

    <div class="w-full bg-gray-700">
      <table
        class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400"
      >
        <thead
          class="text-xs text-gray-700 uppercase bg-gray-100 dark:bg-gray-700 dark:text-gray-400"
        >
          <tr>
            <th scope="col" class="px-6 py-3">
              Description
            </th>
            <th scope="col" class="px-6 py-3 rounded-e-lg">
              Target Date
            </th>
            <th scope="col" class="px-6 py-3 rounded-e-lg">
              Is Done?
            </th>
            <th
              scope="col"
              class="px-6 py-3 rounded-e-lg"
            ></th>
            <th
              scope="col"
              class="px-6 py-3 rounded-e-lg"
            ></th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${todos}" var="todo">
            <tr class="bg-white dark:bg-gray-800">
              <td class="px-6 py-4">${todo.description}</td>
              <td class="px-6 py-4">${todo.targetDate}</td>
              <td class="px-6 py-4">${todo.done}</td>
              <td class="px-6 py-4">
                <a href="delete-todo?id=${todo.id}"
                  >Delete</a
                >
              </td>
              <td class="px-6 py-4">
                <a href="update-todo?id=${todo.id}"
                  >Update</a
                >
              </td>
            </tr>
          </c:forEach>
        </tbody>
        <tfoot>
          <tr
            class="font-semibold text-gray-900 dark:text-white"
          >
            <th scope="row" class="px-6 py-3 text-base">
              Total
            </th>
            <td class="px-6 py-3">3</td>
            <td class="px-6 py-3">21,000</td>
          </tr>
        </tfoot>
      </table>
      <a href="add-todo">Add Todo</a>
    </div>
  </body>
</html>

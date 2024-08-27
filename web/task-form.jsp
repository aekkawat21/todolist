<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Task Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: #ffffff;
            padding: 30px;
            box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            width: 100%;
            max-width: 450px;
        }

        h1 {
            font-size: 24px;
            margin-bottom: 25px;
            text-align: center;
            color: #333333;
        }

        p {
            margin-bottom: 20px;
            font-size: 16px;
            color: #555555;
        }

        input[type="text"],
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #dddddd;
            border-radius: 6px;
            font-size: 14px;
            margin-top: 5px;
            box-sizing: border-box;
        }

        textarea {
            resize: vertical;
            height: 120px;
        }

        input[type="checkbox"] {
            margin-right: 10px;
            transform: scale(1.2);
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
            box-sizing: border-box;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .back-link {
            display: block;
            margin-top: 20px;
            text-align: center;
            text-decoration: none;
            color: #4CAF50;
            font-size: 16px;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>${task != null ? 'Edit Task' : 'New Task'}</h1>
        <form action="${task != null ? 'update' : 'insert'}" method="post">
            <input type="hidden" name="id" value="${task.id}" />
            <p>Title: <input type="text" name="title" value="${task.title}" /></p>
            <p>Description: <textarea name="description">${task.description}</textarea></p>
            <p>Done: 
                <label>
                    <input type="checkbox" name="done" ${task != null && task.done ? 'checked' : ''} 
                        onclick="this.value=this.checked ? 'true' : 'false'" />
                    Mark as done
                </label>
            </p>
            <p><input type="submit" value="${task != null ? 'Update' : 'Save'}" /></p>
        </form>
        <a href="list" class="back-link">Back to list</a>
    </div>
</body>
</html>

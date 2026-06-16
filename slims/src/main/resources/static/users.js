const API_URL = "/api/users";

document
    .getElementById("userForm")
    .addEventListener(
        "submit",
        createUser);

loadUsers();

async function createUser(event) {

    event.preventDefault();

    const user = {

        employeeCode:
            document.getElementById("employeeCode").value,

        firstName:
            document.getElementById("firstName").value,

        lastName:
            document.getElementById("lastName").value,

        email:
            document.getElementById("email").value,

        password:
            document.getElementById("password").value,

        role:
            document.getElementById("role").value
    };

    await fetch(API_URL, {

        method: "POST",

        headers: {
            "Content-Type":"application/json"
        },

        body: JSON.stringify(user)

    });

    loadUsers();

    document
        .getElementById("userForm")
        .reset();
}

async function loadUsers() {

    const response = await fetch(API_URL);

    const users = await response.json();

    let rows = "";

    users.forEach(user => {

        rows += `

        <tr>

            <td>${user.id}</td>

            <td>${user.firstName}</td>

            <td>${user.email}</td>

            <td>${user.role}</td>

        </tr>

        `;
    });

    document
        .getElementById("userTableBody")
        .innerHTML = rows;
}
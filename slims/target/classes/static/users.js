const API_URL = "/api/users";

let allUsers = [];

document
    .getElementById("userForm")
    .addEventListener("submit", saveUser);

loadUsers();

async function saveUser(event) {

    event.preventDefault();

    const userId =
        document.getElementById("userId").value;

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

    const method =
        userId ? "PUT" : "POST";

    const url =
        userId
            ? `${API_URL}/${userId}`
            : API_URL;

    try {

        const response =
            await fetch(url, {

                method: method,

                headers: {
                    "Content-Type":
                        "application/json"
                },

                body:
                    JSON.stringify(user)
            });

        if(response.ok){

            showSuccess(
                userId
                    ? "User Updated Successfully"
                    : "User Created Successfully"
            );

            clearForm();

            loadUsers();

        } else {

            const error =
                await response.text();

            showError(error);
        }

    } catch(error){

        showError(
            "Server Connection Failed");
    }
}

async function loadUsers() {

    try {

        const response =
            await fetch(API_URL);

        allUsers =
            await response.json();

        renderUsers(allUsers);

    } catch(error){

        showError(
            "Unable to load users");
    }
}

function renderUsers(users){

    let rows = "";

    users.forEach(user => {

        rows += `

        <tr>

            <td>${user.id}</td>

            <td>${user.employeeCode}</td>

            <td>${user.firstName} ${user.lastName ?? ""}</td>

            <td>${user.email}</td>

            <td>${user.role}</td>

            <td>${user.active}</td>

            <td>

                <button
                    class="btn btn-warning btn-sm"
                    onclick="editUser(${user.id})">

                    Edit

                </button>

                <button
                    class="btn btn-danger btn-sm"
                    onclick="deleteUser(${user.id})">

                    Delete

                </button>

            </td>

        </tr>

        `;
    });

    document
        .getElementById("userTableBody")
        .innerHTML = rows;
}

async function editUser(id){

    const response =
        await fetch(
            `${API_URL}/${id}`);

    const user =
        await response.json();

    document.getElementById("userId").value =
        user.id;

    document.getElementById("employeeCode").value =
        user.employeeCode;

    document.getElementById("firstName").value =
        user.firstName;

    document.getElementById("lastName").value =
        user.lastName;

    document.getElementById("email").value =
        user.email;

    document.getElementById("role").value =
        user.role;

    window.scrollTo({
        top:0,
        behavior:"smooth"
    });
}

async function deleteUser(id){

    if(!confirm(
        "Delete this user?")){

        return;
    }

    const response =
        await fetch(
            `${API_URL}/${id}`,
            {
                method:"DELETE"
            });

    if(response.ok){

        showSuccess(
            "User Deleted");

        loadUsers();

    }else{

        showError(
            "Delete Failed");
    }
}

function searchUsers(){

    const keyword =
        document
            .getElementById(
                "searchInput")
            .value
            .toLowerCase();

    const filteredUsers =
        allUsers.filter(user =>

            user.firstName
                .toLowerCase()
                .includes(keyword)

            ||

            user.email
                .toLowerCase()
                .includes(keyword)

            ||

            user.employeeCode
                .toLowerCase()
                .includes(keyword)
        );

    renderUsers(filteredUsers);
}

function clearForm(){

    document
        .getElementById("userForm")
        .reset();

    document
        .getElementById("userId")
        .value = "";
}

function showSuccess(message){

    document
        .getElementById("message")
        .innerHTML =

        `<div class="alert alert-success">
            ${message}
         </div>`;
}

function showError(message){

    document
        .getElementById("message")
        .innerHTML =

        `<div class="alert alert-danger">
            ${message}
         </div>`;
}
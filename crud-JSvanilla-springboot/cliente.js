//Definición de variables
const url = "http://localhost:8080/usuario/";
const contenedor = document.querySelector("tbody");
const modalDialog = document.querySelector(".modal-dialog");
let resultados = "";

const modalUsuario = new bootstrap.Modal(
  document.getElementById("modalUsuario")
);
const formUsuario = document.querySelector("form");
const nombre = document.getElementById("nombre");
const email = document.getElementById("email");
const prioridad = document.getElementById("prioridad");
var opcion = "";

btnCrear.addEventListener("click", () => {
  formUsuario.reset();
  modalUsuario.show();
  opcion = "crear";
});

//funcion para mostrar los resultados
const mostrar = (usuarios) => {
  usuarios.forEach((usuario) => {
    resultados += `<tr>
                            <td>${usuario.id}</td>
                            <td>${usuario.nombre}</td>
                            <td>${usuario.email}</td>
                            <td>${usuario.prioridad}</td>
                            <td class="text-center"><a class="btnEditar btn btn-primary">Editar</a><a class="btnBorrar btn btn-danger">Borrar</a></td>
                       </tr>
                    `;
  });
  contenedor.innerHTML = resultados;
};

//funcion para mostrar los resultados buscados por el email.
const mostrarByEmail = (usuario) => {
  contenedor.innerHTML = "";
  resultados = `<tr>
                              <td>${usuario.id}</td>
                              <td>${usuario.nombre}</td>
                              <td>${usuario.email}</td>
                              <td>${usuario.prioridad}</td>
                              <td class="text-center"><a class="btnEditar btn btn-primary">Editar</a><a class="btnBorrar btn btn-danger">Borrar</a></td>
                         </tr>
                      `;
  contenedor.innerHTML = resultados;
};

//Procedimiento Mostrar
fetch(url)
  .then((response) => response.json())
  .then((data) => mostrar(data))
  .catch((error) => console.log(error));

const on = (element, event, selector, handler) => {
  element.addEventListener(event, (e) => {
    if (e.target.closest(selector)) {
      handler(e);
    }
  });
};

//Procedimiento Buscar por correo
on(document, "click", "#btnBuscar", (e) => {
  const email = inputEmail.value;

  if (validacionEmail()) {
    alertify.confirm(
      "This is a confirm dialog.",
      function () {
        fetch(url + "email/" + email, {
          method: "GET",
        })
          .then((response) => response.json())
          .then((data) => {
            mostrarByEmail(data);
          })
          .catch((error) => console.log(error));
        const alertOk = alertify.success("Ok");
      },
      function () {
        alertify.error("Cancel");
      }
    );
  }
});

//Procedimiento Eliminar un usuario por el correo.
on(document, "click", "#btnEliminar", (e) => {
  if (validacionEmail()) {
    const email = inputEmail.value;
    alertify.confirm(
      "This is a confirm dialog.",
      function () {
        fetch(url + "email/" + email, {
          method: "DELETE",
        })
          .then((res) => {
            res.json();
          })
          .then(() => location.reload());
        alertify.success("Ok");
      },
      function () {
        alertify.error("Cancel");
      }
    );
  }
});

//Procedimiento Borrar
on(document, "click", ".btnBorrar", (e) => {
  const fila = e.target.parentNode.parentNode;
  const id = fila.firstElementChild.innerHTML;
  alertify.confirm(
    "This is a confirm dialog.",
    function () {
      fetch(url + id, {
        method: "DELETE",
      })
        .then((res) => {
          res.json();
        })
        .then(() => location.reload());
      alertify.success("Ok");
    },
    function () {
      alertify.error("Cancel");
    }
  );
});

//Procedimiento Editar
let idForm = 0;
on(document, "click", ".btnEditar", (e) => {
  const fila = e.target.parentNode.parentNode;
  idForm = fila.children[0].innerHTML;
  const nombreForm = fila.children[1].innerHTML;
  const emailForm = fila.children[2].innerHTML;
  const prioridadForm = fila.children[3].innerHTML;
  nombre.value = nombreForm;
  email.value = emailForm;
  prioridad.value = prioridadForm;
  opcion = "editar";
  modalUsuario.show();
});

//Procedimiento para Crear y Editar
formUsuario.addEventListener("submit", (e) => {
  e.preventDefault();

  console.log(validacionForm());
  if (validacionForm()) {
    if (opcion == "crear") {
      createUser();
    }
    if (opcion == "editar") {
      updateUser();
    }
    modalUsuario.hide();
  }
});

//Procedimiento para cear un Usuario.
const createUser = () => {
  //console.log('OPCION CREAR')
  fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      nombre: nombre.value,
      email: email.value,
      prioridad: prioridad.value,
    }),
  })
    .then((response) => response.json())
    .then((data) => {
      const nuevoUsuario = [];
      nuevoUsuario.push(data);
      mostrar(nuevoUsuario);
    });
};

//Procedimiento para editar  un Usuario.
const updateUser = () => {
  //console.log('OPCION EDITAR')
  fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      id: idForm,
      nombre: nombre.value,
      email: email.value,
      prioridad: prioridad.value,
    }),
  })
    .then((response) => response.json())
    .then(() => location.reload());
};

//Procedimiento para validar los datos del formulario de crear y editar
const validacionForm = () => {
  if (nombre.value.length < 1) {
    return false;
  }

  if (email.value.length < 1) {
    return false;
  }

  if (prioridad.value.length <= 0) {
    return false;
  }

  return true;
};

//Procedimiento para validar los datos del formulario del correo
const validacionEmail = () => {
  if (inputEmail.value.length < 2) {
    return false;
  }
  return true;
};

// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })
})()

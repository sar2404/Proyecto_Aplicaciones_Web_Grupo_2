// Carga los datos del usuario en el modal Agregar o Editar
document.addEventListener('DOMContentLoaded', function () {
    const modal = document.getElementById('usuarioModal');
    if (!modal) {
        return;
    }
    const form = document.getElementById('usuarioForm');
    const header = document.getElementById('usuarioModalHeader');
    const titulo = document.getElementById('usuarioModalTitulo');
    const id = document.getElementById('usuarioId');
    const nombre = document.getElementById('usuarioNombre');
    const correo = document.getElementById('usuarioCorreo');
    const contrasena = document.getElementById('usuarioContrasena');
    const rol = document.getElementById('usuarioRol');
    modal.addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget;
        if (!button) {
            return;
        }
        form.reset();
        id.value = '';
        titulo.textContent = button.getAttribute('data-titulo') || '';
        header.classList.remove('bg-info', 'bg-warning', 'text-white');
        if (button.getAttribute('data-modo') === 'editar') {
            id.value = button.getAttribute('data-id') || '';
            nombre.value = button.getAttribute('data-nombre') || '';
            correo.value = button.getAttribute('data-correo') || '';
            rol.value = button.getAttribute('data-rol') || '';
            contrasena.required = false;
            header.classList.add('bg-warning');
        } else {
            contrasena.required = true;
            header.classList.add('bg-info', 'text-white');
        }
    });
});

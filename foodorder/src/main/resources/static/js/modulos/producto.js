// Carga los datos del producto en el modal Agregar o Editar
document.addEventListener('DOMContentLoaded', function () {
    const modal = document.getElementById('productoModal');
    if (!modal) {
        return;
    }
    const form = document.getElementById('productoForm');
    const header = document.getElementById('productoModalHeader');
    const titulo = document.getElementById('productoModalTitulo');
    const id = document.getElementById('productoId');
    const categoria = document.getElementById('productoCategoria');
    const nombre = document.getElementById('productoNombre');
    const descripcion = document.getElementById('productoDescripcion');
    const precio = document.getElementById('productoPrecio');
    const estado = document.getElementById('productoEstado');
    const imagen = document.getElementById('productoImagen');
    const preview = document.getElementById('productoPreview');

    function mostrarPreview(ruta) {
        if (ruta) {
            preview.src = ruta;
            preview.classList.remove('d-none');
        } else {
            preview.removeAttribute('src');
            preview.classList.add('d-none');
        }
    }

    imagen.addEventListener('input', function () {
        mostrarPreview(this.value);
    });

    modal.addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget;
        if (!button) {
            return;
        }
        const modo = button.getAttribute('data-modo');
        form.reset();
        id.value = '';
        titulo.textContent = button.getAttribute('data-titulo') || '';
        header.classList.remove('bg-info', 'bg-warning', 'text-white');
        if (modo === 'editar') {
            id.value = button.getAttribute('data-id') || '';
            categoria.value = button.getAttribute('data-categoria') || '';
            nombre.value = button.getAttribute('data-nombre') || '';
            descripcion.value = button.getAttribute('data-descripcion') || '';
            precio.value = button.getAttribute('data-precio') || '';
            estado.checked = button.getAttribute('data-estado') === 'true';
            imagen.value = button.getAttribute('data-imagen') || '';
            header.classList.add('bg-warning');
        } else {
            estado.checked = true;
            header.classList.add('bg-info', 'text-white');
        }
        mostrarPreview(imagen.value);
    });
});

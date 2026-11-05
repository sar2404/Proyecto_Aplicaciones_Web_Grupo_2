// Carga los datos de la categoria en el modal Agregar o Editar
document.addEventListener('DOMContentLoaded', function () {
    const modal = document.getElementById('categoriaModal');
    if (!modal) {
        return;
    }
    const form = document.getElementById('categoriaForm');
    const header = document.getElementById('categoriaModalHeader');
    const titulo = document.getElementById('categoriaModalTitulo');
    const id = document.getElementById('categoriaId');
    const nombre = document.getElementById('categoriaNombre');
    const descripcion = document.getElementById('categoriaDescripcion');
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
            descripcion.value = button.getAttribute('data-descripcion') || '';
            header.classList.add('bg-warning');
        } else {
            header.classList.add('bg-info', 'text-white');
        }
    });
});

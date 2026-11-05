// Carga en el modal de eliminar el registro seleccionado
document.addEventListener('DOMContentLoaded', function () {
    const confirmModal = document.getElementById('confirmModal');
    if (!confirmModal) {
        return;
    }
    confirmModal.addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget;
        document.getElementById('modalId').value = button.getAttribute('data-bs-id');
        document.getElementById('modalDescripcion').textContent = button.getAttribute('data-bs-descripcion');
    });
});

// Quita los toast despues de 4 segundos
document.addEventListener('DOMContentLoaded', function () {
    setTimeout(() => {
        document.querySelectorAll('.toast').forEach(toast => toast.classList.remove('show'));
    }, 4000);
});

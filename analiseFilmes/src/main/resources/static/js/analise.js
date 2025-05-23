$(document).ready(function() {
    $('#formAnalise').on('submit', function(e) {
        e.preventDefault();
        enviarAnalise();
    });
});

function enviarAnalise() {
    const analise = {
        filmeId: $('#filmeId').val(),
        comentario: $('#comentario').val(),
        nota: $('#nota').val()
    };

    if (!analise.comentario || !analise.nota) {
        alert('Preencha todos os campos obrigatórios!');
        return;
    }

    $.ajax({
        url: '/analises',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(analise),
        success: function() {
            // Limpa o formulário e recarrega as análises
            $('#comentario').val('');
            $('#nota').val('');
            location.reload(); // Recarrega a página para mostrar a nova análise
        },
        error: function(xhr) {
            const errorMsg = xhr.responseJSON?.message || 'Erro desconhecido';
            alert('Erro ao enviar análise: ' + errorMsg);
        }
    });
}
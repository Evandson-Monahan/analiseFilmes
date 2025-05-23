$(document).ready(function() {
    // Configura o evento de submit do formulário
    $('#formFilme').on('submit', function(e) {
        e.preventDefault();
        salvarFilme();
    });
});

function salvarFilme() {
    const filmeData = {
        id: $("#id").val() ? parseInt($("#id").val()) : null,
        titulo: $("#titulo").val(),
        sinopse: $("#sinopse").val(),
        genero: $("#genero").val(),
        anoLancamento: parseInt($("#anoLancamento").val())
    };

    // Validação básica no cliente
    if (!filmeData.titulo || !filmeData.genero || isNaN(filmeData.anoLancamento)) {
        showAlert('Preencha todos os campos obrigatórios corretamente!', 'danger');
        return;
    }

    $.ajax({
        url: '/filmes/salvar',
        type: 'POST',
        contentType: 'application/json', // Isso é crucial
        data: JSON.stringify(filmeData),
        success: function(response) {
            showAlert('Filme salvo com sucesso!', 'success');
            setTimeout(() => {
                window.location.href = '/filmes'; // Redireciona para a lista
            }, 1500);
        },
        error: function(xhr) {
            const errorMsg = xhr.responseJSON?.error || xhr.statusText;
            showAlert('Erro ao salvar filme: ' + errorMsg, 'danger');
            console.error('Detalhes do erro:', xhr.responseJSON);
        }
    });
}

function showAlert(message, type) {
    const alertHtml = `
        <div class="alert alert-${type} alert-dismissible fade show" role="alert">
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    `;
    $('#alert-container').html(alertHtml);
    
    // Remove o alerta após 5 segundos
    setTimeout(() => {
        $('.alert').alert('close');
    }, 5000);
}

function carregarFilmes() {
    $.ajax({
        url: '/filmes',
        type: 'GET',
        dataType: 'json',
        success: function(response) {
            if (!Array.isArray(response)) {
                console.error("Resposta inesperada:", response);
                showAlert('Erro ao carregar filmes: formato inválido', 'danger');
                return;
            }
            
            let tabela = $('#tabelaFilmes tbody');
            tabela.empty();
            
            response.forEach(filme => {
                tabela.append(`
                    <tr>
                        <td>${filme.titulo}</td>
                        <td>${filme.genero}</td>
                        <td>
                            <a href="/filmes/${filme.id}">Detalhes</a>
                            <a href="/filmes/editar/${filme.id}">Editar</a>
                            <button onclick="deletarFilme(${filme.id})">Excluir</button>
                        </td>
                    </tr>
                `);
            });
        },
        error: function(xhr, status, error) {
            console.error("Erro na requisição:", error);
            showAlert('Erro ao carregar filmes!', 'danger');
        }
    });
}

function deletarFilme(id) {
    if (confirm('Tem certeza que deseja excluir este filme?')) {
        $.ajax({
            url: '/filmes/excluir/' + id, // Corrigido
            type: 'POST', // Alterado para compatibilidade
            success: function() {
                showAlert('Filme excluído com sucesso!', 'success');
                setTimeout(() => {
                    window.location.reload();
                }, 1000);
            },
            error: function(xhr) {
                showAlert('Erro ao excluir filme: ' + xhr.responseText, 'danger');
            }
        });
    }
}

function showAlert(message, type) {
    const alertHtml = `
        <div class="alert alert-${type} alert-dismissible fade show" role="alert">
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    `;
    $('#alert-container').html(alertHtml);
}
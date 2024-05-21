$(document).ready(function() {
    $('#recipeForm').on('submit', function(event) {
        event.preventDefault();

        // Create a recipe object based on form data
        var recipe = {
        name: $('#name').val(),
        description: $('#description').val(),
        ingredients: $('#ingredients').val(),
        instructions: $('#instructions').val()
        };

        // AJAX call to the server
        $.ajax({
            url: '/users/addRecipe',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(recipe),
            success: function(response) {
                alert('Recipe added successfully!');
                console.log(response);
                window.location.href = 'main2.html';
            },
            error: function(error) {
                alert('Error adding recipe');
                console.error(error);
            }
        });
    });
});

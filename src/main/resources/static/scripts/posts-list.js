(function(){

    var request = $.ajax({
        url: '/posts.json'
    });

    request.done(function (posts) { // The HTTP Response -> an array of JSON objects -> posts

        var i, html = '';

        for (i = 0; i < posts.length; i++ ){
            html+= '<div><h2>' + posts[i].title + '</h2><p>' + posts[i].body + '</p>' + '<img src="/uploads/' + posts[i].image + '"alt="No Image"/>' + '</div>'
        }

        $('#load-posts').html(html);

    });

})();
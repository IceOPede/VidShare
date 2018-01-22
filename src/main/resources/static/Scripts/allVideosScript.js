var getUrl = window.location.host;

$(document).ready(function () {
    $.ajax({
        url: "https://"+getUrl+"/getVideos"
    }).then(function (value) {

        for (i = 0; i < value.length; i++) {
            if (value[i].type == "VIDEO") {
                var para = document.createElement("video");
                para.className = "responsive-video";
                para.setAttribute("controls", true);
                var node = document.createElement("source");
                node.setAttribute("src", "" + value[i].url);
                node.setAttribute("type", "video/mp4");
                para.appendChild(node);

                var container = document.createElement("div");
                container.setAttribute("class", "section");

                var row = document.createElement("div");
                row.setAttribute("class", "row");

                var button = document.createElement("button");
                button.className = "btn right-align waves-effect waves-light col s4";
                button.setAttribute("type", "submit");
                button.setAttribute("name", "action");
                button.setAttribute("id", value[i].name);
                button.setAttribute("onclick", "liked(this.id)");

                var likes = document.createElement("span");
                likes.setAttribute("class", "black-text col s1 offset-s7");
                likes.setAttribute("id", "text/" + value[i].name);
                likes.style["font-size"] = "25px";
                var likesText = document.createTextNode(value[i].likes);
                likes.appendChild(likesText);


                var icon = document.createElement("i");
                icon.setAttribute("class", "material-icons right");
                var iconText = document.createTextNode("favorite_border");
                icon.appendChild(iconText);
                button.appendChild(icon);

                var buttonText = document.createTextNode("Like");
                button.appendChild(buttonText);

                row.appendChild(likes);
                row.appendChild(button);


                var element = document.getElementById("videoContainer");
                element.appendChild(para);
                element.appendChild(row);
            } else if (value[i].type == "LINK"){

                var para = document.createElement("div");
                para.className = "video-container";
                var node = document.createElement("iframe");
                node.setAttribute("src", value[i].name);
                node.setAttribute("width", "853");
                node.setAttribute("height", "480");
                node.setAttribute("frameborder", "0");

                para.appendChild(node);

                var container = document.createElement("div");
                container.setAttribute("class", "section");

                var row = document.createElement("div");
                row.setAttribute("class", "row");

                var button = document.createElement("button");
                button.className = "btn right-align waves-effect waves-light col s4";
                button.setAttribute("type", "submit");
                button.setAttribute("name", "action");
                button.setAttribute("id", value[i].name);
                button.setAttribute("onclick", "liked(this.id)");

                var likes = document.createElement("span");
                likes.setAttribute("class", "black-text col s1 offset-s7");
                likes.setAttribute("id", "text/" + value[i].name);
                likes.style["font-size"] = "25px";
                var likesText = document.createTextNode(value[i].likes);
                likes.appendChild(likesText);


                var icon = document.createElement("i");
                icon.setAttribute("class", "material-icons right");
                var iconText = document.createTextNode("favorite_border");
                icon.appendChild(iconText);
                button.appendChild(icon);

                var buttonText = document.createTextNode("Like");
                button.appendChild(buttonText);

                row.appendChild(likes);
                row.appendChild(button);


                var element = document.getElementById("videoContainer");
                element.appendChild(para);
                element.appendChild(row);

            }
        }
    })
});

function liked(clicked_id) {
    $.ajax({
        type: "POST",
        url: "https://"+getUrl+"/like",
        data: {name: clicked_id} // parameters
    }).then(function (value) {
        document.getElementById("text/" + clicked_id).innerText = value;
    })


    // var button = document.getElementById(clicked_id);
    // button.className = "btn right-align waves-effect waves-light col s4 disabled";
    // button.setAttribute("type", "submit");
    // button.setAttribute("name", "action");
    // button.setAttribute("id", value[i].name);
    // button.setAttribute("onclick", "liked(this.id)");
}
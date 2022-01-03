function getInfo(){
    var name = document.getElementById("student_name").value;
    var id = document.getElementById("student_id").value;
    var email = document.getElementById("student_email").value;
    var photo = document.getElementById("student_photo").value;

    var myObj = {
        "name": name,
        "id": id,
        "email": email,
        "photo": photo
    };

    sessionStorage.setItem(id,JSON.stringify(myObj));
    stud_id = id;
    createId(id);

}

function removeForm(){
    var format = document.getElementById("inputForm");
    format.remove();
}

function createId(){
    window.alert(sessionStorage.length);
    if(sessionStorage.length > 0)
    {
        removeForm();
        var obj = JSON.parse(sessionStorage.getItem(sessionStorage.key(0)));
        
        var cardForm = document.createElement("div");
        cardForm.style.height="200px";
        cardForm.style.width="400px";
        cardForm.style.border="7px solid";
        document.body.appendChild(cardForm);

        var headLine = document.createElement("h3");
        headLine.innerHTML = "신분증";
        headLine.style.textAlign = "center";
        cardForm.appendChild(headLine);

        var photoArea = document.createElement("img");
        photoArea.src = obj.photo;
        photoArea.style.float = "right";
        photoArea.style.width = "30%";
        photoArea.style.height = "auto";
        photoArea.alt = "student_photo";

        cardForm.appendChild(photoArea);

        var nameLabel = document.createElement("h4");
        nameLabel.innerHTML = "이름: " + obj.name;
        cardForm.appendChild(nameLabel);

        var idLabel = document.createElement("h4");
        idLabel.innerHTML = "학번: " + obj.id;
        cardForm.appendChild(idLabel);

        var emailLabel = document.createElement("h4");
        emailLabel.innerHTML = "이메일: " + obj.email;
        cardForm.appendChild(emailLabel);
    }
       
}

function init(){
    document.addEventListener("submit",getInfo);
    
    createId();
}

window.addEventListener("load",init);
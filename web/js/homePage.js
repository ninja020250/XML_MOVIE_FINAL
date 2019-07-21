var xmlDom;
var listFilmObj;
var showingData;
var perpage = 12;
var currentpage = 1;
var searchValue = "";
var keywords = [];
var filmResult = undefined;
var listQuizObj = [];
var poolAnalysis = [];
var listRecommandSpecial = [];
window.onload = function (e) {
    var xmlDOMFilm = convertStringToXML(films);
    convertXMLDOMToOBject(xmlDOMFilm);
    var xmlDOMQuiz = convertStringToXML(quizs);
    convertXMLQuiztoObject(xmlDOMQuiz)
    setUpquiz();
    specialRecommand();
    generatespecialRecommand(listRecommandSpecial);
}
function onPressSearch(e) {
    if (e.keyCode == 13) {
        searchData();
    }
}

function specialRecommand() {
    var history = document.getElementById("history").value;
    var his = [];

    his = history.split(",");
    for (var i = 0; i < listFilmObj.length; i++) {

        for (var j = 0; j < his.length; j++) {

            if (listFilmObj[i].kinds.includes(his[j])) {

                listFilmObj[i].isMatch = true;
            }
        }
    }

    for (var k = 0; k < listFilmObj.length; k++) {
        if (listFilmObj[k].isMatch) {
            listRecommandSpecial.push(listFilmObj[k]);
        }
    }

    if (listRecommandSpecial.length > 10) {
        listRecommandSpecial = listRecommandSpecial.splice(0, 10);
    }

//    console.log(listRecommandSpecial);

}
var generatespecialRecommand = function (listRecommandSpecial) {
    var section = document.getElementById("specialRecommand");
    section.innerHTML = '';
    var h1 = document.createElement("h1");


    if (listRecommandSpecial !== undefined && listRecommandSpecial.length > 0) {
        h1.textContent = "Gợi Ý Cho bạn:";
        h1.style.paddingTop = "10vh";
        h1.style.paddingLeft = "2vw";
        section.appendChild(h1);
        var container = document.createElement("div");
        container.className = "film-container";
        for (var i = 0; i < listRecommandSpecial.length; i++) {
            var form = generateFilmElemet(listRecommandSpecial[i]);
            container.appendChild(form);
        }
        section.appendChild(container);
    }

}
function listProduct() {
    this.listFilmObj = [];
}
//window.document.onload = function (e) {
//    var li = document.getElementByClassName("li-pagi");
//    li.onclick = function(){
//        debugger;
//          searchData();
//    }
//}
var goFilmDetail = function (id) {
    var form = document.createElement("form");
    var element1 = document.createElement("input");
    form.method = "GET";
    form.action = "FilmDetailServlet";
    element1.value = id;
    element1.name = "filmID";
    form.appendChild(element1);
    document.body.appendChild(form);
    form.submit();
}

var convertStringToXML = function (xmlString) {

    var parser = new DOMParser();
    xmlDom = parser.parseFromString(xmlString, "text/xml");
    return xmlDom;
};
//var convertXMLtoObj = function(){
//    var x = xmlDom.getElementsByTagName("ns2:listFilm");
//    
//    console.log(x);
//}
var getAllFieldOfNode = function (item) {
    var childs = item.childNodes;
    var film = new Object();
    var kinds = [];
    for (var i = 0; i < childs.length; i++) {
        var child = childs[i];
        switch (child.nodeName) {
            case "filmID":
                film.filmID = child.textContent;
                break;
            case "time":
                film.time = child.textContent;
                break;
            case "duration":
                film.duration = child.textContent;
                break;
            case"limitAge":
                film.limitAge = child.textContent;
                break;
            case "filmName":
                film.filmName = child.textContent;
                break;
            case "rate":
                film.rate = child.textContent;
                break;
            case "kindOfFilms":
                kinds.push(child.textContent);
                break;
            case "imageURL":
                film.imageURL = child.textContent;
                break;
            case "numberOfView":
                film.numberOfView = child.textContent;
                break;
            case "engName":
                film.engName = child.textContent;
                break;
            case "searchContent":
                film.searchContent = child.textContent;
            default:
                break;
        }
    }
    film.star = 0;
    film.kinds = Object.assign([], kinds);
    return film;
}
function signUp() {
    var form = document.createElement("form");
    var element1 = document.createElement("input");
    element1.value = "navigateSignUp";
    element1.name = "btnUser";
    form.method = "POST";
    form.action = "UserLoginServlet";
    form.appendChild(element1);
    document.body.appendChild(form);
    form.submit();
}
var signIn = function () {
    var form = document.createElement("form");
    var element1 = document.createElement("input");
    element1.value = "navigateLogin";
    element1.name = "btnUser";
    form.method = "POST";
    form.action = "UserLoginServlet";
    form.appendChild(element1);
    document.body.appendChild(form);
    form.submit();
}
var logout = function (txtUsername) {

    var form = document.createElement("form");
    var element1 = document.createElement("input");
    var element2 = document.createElement("input");
    element1.value = "logout";
    element1.name = "btnUser";
    element2.value = txtUsername;
    element2.name = "txtUsername";
    form.method = "POST";
    form.action = "UserLogoutServlet";
    form.appendChild(element1);
    document.body.appendChild(form);
    form.submit();
}
var convertXMLDOMToOBject = function (xmlDom) {

    var x = xmlDom.getElementsByTagName("ns2:listFilm");

    listFilmObj = [];
    for (var j = 0; j < x[0].childNodes.length; j++) {
        var film = getAllFieldOfNode(x[0].childNodes[j]);
        if (film !== undefined) {
            listFilmObj.push(film);
        }
    }
//    console.log(listFilmObj);
}
var generateFilmElemet = function (film) {
    var form = document.createElement("form");
    form.addEventListener("click", function () {
        goFilmDetail(film.filmID);
    });
    form.method = "GET";
    form.action = "FilmDetailServlet";
    var filmContainer = document.createElement("div");
    filmContainer.className = "film-container";
    var imageContainer = document.createElement("div");
    imageContainer.className = "image-container";
    var img = document.createElement("img");
    img.src = film.imageURL;
    var filmTitle = document.createElement("div");
    filmTitle.className = "film-title";
    filmTitle.textContent = film.filmName;
    // intergration element
    imageContainer.appendChild(img);
    filmContainer.appendChild(imageContainer);
    filmContainer.appendChild(filmTitle);
    form.appendChild(filmContainer);
    // create variable for servlet go detail
    var input = document.createElement("input");
    input.name = "filmID";
    input.value = film.filmID;
    input.type = "hidden";
    form.appendChild(input);
    return form;
}
var generateResultSearch = function (result) {
    var section = document.getElementById("searchResult");
    section.innerHTML = '';
    var h1 = document.createElement("h1");
    h1.textContent = "Kết quả tìm kiếm:";
    h1.style.paddingTop = "10vh";
    h1.style.paddingLeft = "2vw";
    section.appendChild(h1);
    if (result != undefined && result.length > 0) {
        var container = document.createElement("div");
        container.className = "film-container";
        for (var i = 0; i < result.length; i++) {
            var form = generateFilmElemet(result[i]);
            container.appendChild(form);
        }
        section.appendChild(container);
    }

}
var createLi = function (i, callback, type) {
    var li = document.createElement("li");
    li.textContent = i;
    li.style.padding = ".5rem .5rem";
    li.style.cursor = "pointer";
    li.className = "li-pagi";
    if (currentpage === i) {
        li.style.color = "#f1c40f";
    }
    li.addEventListener("click", function () {
        currentpage = i;
//        searchData();
        callback();
        createPageNumbers(type);
    });
    return li;
}
var createLimitLi = function (title, index, callback, type) {
    var li = document.createElement("li");
    li.textContent = title;
    li.style.padding = ".5rem .5rem";
    li.style.cursor = "pointer";
    li.className = "li-pagi";
    
//   debugger;
    li.addEventListener("click", function () {
        currentpage = index;
     
//        searchData();
        callback();
        createPageNumbers(type);
    });
    return li;
}
var createPageNumbers = function (type) {
    var pagiContainer = document.getElementById("pagi-container");
    pagiContainer.innerHTML = "";
    var numberOfpage = 0;
    var pages = document.createElement("ul");
    pages.style.color = "#ffffff";
    pages.style.display = "flex";
    pages.style.listStyleType = " none";
    var callback = undefined;
    if (type === "SEARCH") {
        callback = function () {
            searchData();
        }
    } else {
        callback = function () {
            filterData();
        }
    }
    if (showingData !== undefined && showingData.length > 0) {
        var numberOfPage = Math.ceil(showingData.length / perpage);
        var first = createLimitLi("first", 1, callback, type);
        pages.appendChild(first);
        if ((numberOfPage) > 8) {
            if (currentpage > 8) {
                if ((numberOfPage - currentpage) < 3) {
                    for (var i = currentpage-1; i <= numberOfPage; i++) {
                        var li = createLi(i, callback, type);
                        pages.appendChild(li)

                    }
                } else {
                    for (var i = currentpage-1; i <= currentpage + 3; i++) {
                        var li = createLi(i, callback, type);
                        pages.appendChild(li);
                    }
                    var li = createLi(i, callback, type);
                    pages.appendChild(li);

                }
            } else {
                for (var i = 1; i <= 10; i++) {
                    var li = createLi(i, callback, type);
                    pages.appendChild(li);
                }
            }
        } else {
            for (var i = 1; i <= numberOfPage; i++) {
                var li = createLi(i, callback, type);
                pages.appendChild(li);
            }
        }
        var last = createLimitLi("last", numberOfPage, callback, type);
        pages.appendChild(last);
        pagiContainer.appendChild(pages);

    }
}
var getDataFollowPagi = function (data) {
    const indexOfLastTodo = currentpage * perpage;
    const indexOfFirstTodo = indexOfLastTodo - perpage;
    const result = data.slice(indexOfFirstTodo, indexOfLastTodo);
    return result;
}
var searchData = function () {
    searchValue = document.getElementById("searchBox").value;
    var poolData = [];
    showingData = [];
    if (searchValue !== undefined && searchValue !== "") {
        for (var i = 0; i < listFilmObj.length; i++) {
            var film = Object.assign({}, listFilmObj[i]);
//            var searchContent = film.filmName + film.engName;
//            debugger;
            if (film.searchContent.toLowerCase().includes(searchValue.toLowerCase())) {
                showingData.push(film);
            }
        }

        var dataPagi = getDataFollowPagi(showingData);
        generateResultSearch(dataPagi);
        createPageNumbers("SEARCH");
    }
}


var filterData = function () {
    filterValue = document.getElementById("filter").value;
    var poolData = [];
    showingData = [];

    if (filterValue !== undefined && filterValue !== "") {
        for (var i = 0; i < listFilmObj.length; i++) {
            var film = Object.assign({}, listFilmObj[i])
            if (film.kinds.indexOf(filterValue) > 0) {
                showingData.push(film);
            }
        }
        var dataPagi = getDataFollowPagi(showingData);
        generateResultSearch(dataPagi);
        createPageNumbers("FILTER");
    }
}




var countAnswered = 0;
/// quiz javascript
//var generateFakeData = function () {
//    for (var j = 0; j < 5; j++) {
//        var quiz = new Object();
//        quiz.question = `cau hoi so ${j}`;
//        quiz.answers = [];
//        for (var i = 0; i < 4; i++) {
//            var answer = new Object();
//            answer.content = `cau hoi ${i}`;
//            answer.keyword = "key word";
//            quiz.answers.push(answer);
//        }
//        quizs.push(quiz);
//    }
//    console.log(quizs);
//}
var generateFilmResult = function () {
    var filmResultDialog = document.getElementById("filmResultDialog");
    if (filmResult !== undefined) {
        filmResultDialog.innerHTML = "";
        var title = document.createElement("h3");
        title.textContent = "Sau khi phân tích, chúng tôi nghĩ bạn nên xem phim này";
        var img = document.createElement("img");
        img.src = filmResult.imageURL;
        var filmContainerPopup = document.createElement("div");
        filmContainerPopup.className = "film-container-popup";
        var filmName = document.createElement("p");
        filmName.textContent = filmResult.filmName;
        filmName.style.textAlign = "center";
        filmContainerPopup.appendChild(filmName);
        var duration = document.createElement("p");
        duration.textContent = "Thời lượng:" + filmResult.duration;
        filmContainerPopup.appendChild(duration);
        var kindOfFilms = document.createElement("p");
        kindOfFilms.textContent = "Thể loại:" + filmResult.kinds.join(", ");
        filmContainerPopup.appendChild(kindOfFilms);

        var detail = createButton("View Detail", function () {
            goFilmDetail(filmResult.filmID);
        });
        var closebtn = createCancelButtonResult();
        filmContainerPopup.appendChild(detail);
        filmContainerPopup.appendChild(closebtn);
        filmResultDialog.appendChild(title);
        filmResultDialog.appendChild(img);

        filmResultDialog.appendChild(filmContainerPopup);
    }
}

var createAnswer = function (content, keyword, id) {
    var answer = document.createElement("button");
    answer.id = id;
    answer.innerHTML = content;
    answer.className = "btn btn-default";
    answer.addEventListener("click", function () {
        getUserAnswer(keyword);
        setUpquiz();
    });
    return answer;
}
var getUserAnswer = function (keyword) {
    countAnswered += 1;
    keywords.push(keyword);

}
var startQuiz = function () {
    var modal = document.getElementById("quiz-modal");
    modal.style.display = "block";
}
var doneQuiz = function () {
    var modal = document.getElementById("quiz-modal");
    var modalFilmResult = document.getElementById("filmResult");
    modal.style.display = "none";
//    filmResult = listFilmObj[0];
    recommandAnalysis();
    generateFilmResult();
    modalFilmResult.style.display = "block";
    keywords = [];
    countAnswered = 0;
    setUpquiz();
//    filmResult = listFilmObj[0];
}

var convertXMLQuiztoObject = function () {
    var x = xmlDom.getElementsByTagName("ns3:listQuiz");

    listQuizObj = [];
    for (var j = 0; j < x[0].childNodes.length; j++) {
        var quiz = getAllFieldOfQuizNode(x[0].childNodes[j]);
        if (quiz !== undefined) {
            listQuizObj.push(quiz);
        }
    }
//    console.log(listQuizObj);
}
var getAllFieldOfQuizNode = function (item) {
    var childs = item.childNodes;
    var quiz = new Object();
    var answers = [];
    for (var i = 0; i < childs.length; i++) {
        var child = childs[i];
        switch (child.nodeName) {

            case "question":
                quiz.question = child.textContent;
                break;
            case "ns2:Answer":
                var answer = new Object();
                for (var c = 0; c < child.childNodes.length; c++) {
                    var ch = child.childNodes[c];
                    if (ch.nodeName === "ns2:content") {
                        answer.content = ch.textContent;
                    }
                    if (ch.nodeName === "ns2:keyword") {
                        answer.keyword = ch.textContent;
                    }
                }
                answers.push(answer);
                break;
            default:
                break;
        }
    }

    quiz.answers = Object.assign([], answers);
    return quiz;
}
function compareStar(film1, film2) {
    return film2.star - film1.star;
}
var recommandAnalysis = function () {
    var biggeststar = 0;
    var poolkey = [];
    var poolData = [];
    if (keywords.length > 0) {
        for (var i in keywords) {
            var arrkey = keywords[i].split(",");
            poolkey = poolkey.concat(arrkey);
        }
        for (var j in listFilmObj) {
            var film = listFilmObj[j];
            var star = 0;
            for (var k in poolkey) {
                if (film.searchContent.includes(poolkey[k])) {
                    star += 1;
                }
            }
            if (star > 0) {
                var ftemp = Object.assign({}, film);
                ftemp.star = star;
                poolData.push(ftemp);
            }
            if (star > biggeststar) {
                biggeststar = star;
            }
        }
    }
    var tmpTop1 = [];
    var tempTop2 = [];
    for (var o in poolData) {
        if (poolData[o].star === biggeststar) {
            tmpTop1.push(poolData[o]);
        }
        if (poolData[o].star === biggeststar - 1) {
            tempTop2.push(poolData[o]);
        }
    }
    if (tmpTop1.length < 2) {
        tmpTop1 = tmpTop1.concat(tempTop2);
    }
    poolData = Object.assign([], tmpTop1);
//    poolData.sort(compareStar);
    var index = Math.floor(Math.random() * poolData.length);
    filmResult = poolData[index];
//    console.log(filmResult);
}
var setUpquiz = function () {
    // create question
    var htmlQuestion = document.createElement("h3");
    htmlQuestion.id = "question"
    if (countAnswered > 4) {
        doneQuiz();
        console.log("done");
    } else {
        var modalContainer = document.getElementById("modal-container");
        var index = countAnswered;
        htmlQuestion.innerHTML = listQuizObj[index].question;
        var answerContainer = document.createElement("div");
        answerContainer.className = "answer-container";
        for (var a = 0; a < listQuizObj[index].answers.length; a++) {
            var answer = createAnswer(listQuizObj[index].answers[a].content, listQuizObj[index].answers[a].keyword, `answer${a}`);
            answerContainer.appendChild(answer);
        }
//        var answer1 = createAnswer(quizs[index].answers[0].content, quizs[index].answers[0].keyword, "answer1");
////        answer1.classNamess = "btn btn-default";
//        var answer2 = createAnswer(quizs[index].answers[1].content, quizs[index].answers[1].keyword, "answer1");
//        var answer3 = createAnswer(quizs[index].answers[2].content, quizs[index].answers[2].keyword, "answer1");
//        var answer4 = createAnswer(quizs[index].answers[3].content, quizs[index].answers[3].keyword, "answer1");
        modalContainer.innerHTML = "";
        modalContainer.appendChild(htmlQuestion);
        modalContainer.appendChild(answerContainer);
        var cancelbtn = createCancelButtonQuiz();
        modalContainer.appendChild(cancelbtn);
    }
}

// close button 
var createCancelButtonQuiz = function () {
    var cancel = document.createElement("button");
    cancel.innerHTML = "CANCEL";
    cancel.className = "btn btn-default";
    cancel.id = "cancel-modal-quiz";
    cancel.addEventListener("click", function () {
        var quizModal = document.getElementById("quiz-modal");
        if (quizModal !== undefined) {
            quizModal.style.display = "none";
        }

    });
    return cancel;
}

var createCancelButtonResult = function () {
    var cancel = document.createElement("button");
    cancel.innerHTML = "CANCEL";
    cancel.className = "btn btn-default";
    cancel.id = "cancel-modal-result";
    cancel.addEventListener("click", function () {
        var modal = document.getElementById("filmResult");
        if (modal !== undefined) {
            modal.style.display = "none";
        }
    });
    return cancel;
}
var createButton = function (name, callback) {
    var button = document.createElement("button");
    button.innerHTML = name;
    button.className = "btn btn-default";
    button.addEventListener("click", callback);
    return button;
}

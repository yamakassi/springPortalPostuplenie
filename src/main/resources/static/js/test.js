document.addEventListener("DOMContentLoaded", function () {
    /*let map = new Map();
    map.set(1, "инпит");
    map.set(2, "фти");
    map.set(3, "урбас");

    jsonText = JSON.stringify(Array.from(map.entries()));
    console.log(jsonText);*/
    const selectDirection =
        document.getElementsByClassName("select__direction")[0];

    const selectConfirmBtn = document.querySelector(".btn__select-confirm");

    const IwindowButtons = document.querySelectorAll(".js-open-Iwindow"),
        overlay = document.querySelector(".js-overlay-Iwindow"),
        closeButtons = document.querySelectorAll(".js-Iwindow-close"),
        stepOneList = document.getElementById("step-1-app"),
        stepTwoList = document.getElementById("step-2-app"),
        btnApplication = document.getElementById("btn__application"),
    rejectBtn = document.getElementById("rejectBtn");


    const username = document.getElementById("username-hide").innerText;
  if(rejectBtn!=null) {
      rejectBtn.addEventListener("click", (e) => {
          const user = document.getElementById("username-hide").innerText;
          console.log(user);
          e.preventDefault();
          console.log("777")
          const url = "http://localhost:8081/app/" + user + "/delete";
          console.log(url);
          fetch(url, {
              method: "POST",
              headers: {
                  Accept: "application/json",
                  "Content-Type": "application/json",

              },
          })
              .then((data)=>{
                  window.location.reload();
                    console.log(data)
              })

      });
  }
//обработчик клик удаления одного дела
    let closes = function () {
        const value = this.parentNode.dataset.value;

        for (const c of stepTwoList.children) {
            if (c.dataset.priority == value) c.remove();
        }
        this.parentNode.remove();
    };
    let toUp = function () {
        const upElement = this.parentNode.previousElementSibling;
        if (null == upElement) {
            return;
        }

        let upPriotity = this.parentNode.previousElementSibling.dataset.priority,
            upText = this.parentNode.previousElementSibling.children[1].innerText;

        upElement.dataset.priopity = this.parentNode.dataset.priority;

        upElement.children[1].innerText = this.parentNode.children[1].innerText;

        this.parentNode.dataset.priority = upPriotity;
        this.parentNode.children[1].innerText = upText;
    };
    let toDown = function () {
        const downElement = this.parentNode.nextElementSibling;
        if (null == downElement) {
            return;
        }

        let downPriotity = this.parentNode.nextElementSibling.dataset.priority,
            downText = this.parentNode.nextElementSibling.children[1].innerText;

        downElement.dataset.priopity = this.parentNode.dataset.priority;
        console.log(downElement.children);
        downElement.children[1].innerText = this.parentNode.children[1].innerText;

        this.parentNode.dataset.priority = downPriotity;
        this.parentNode.children[1].innerText = downText;
        //  console.log(this.parentNode.nextElementSibling);
    };
    //!модальное окно
    selectConfirmBtn.addEventListener("click", () => {
        let e = document.getElementById("selectView");
        let text = e.options[e.selectedIndex].text;
        let value = e.value;
        addDirections(value, text);
        document.querySelector(".Iwindow.active").classList.remove("active");
        document.querySelector(".overlay").classList.remove("active");
    });
    const addDirections = (value, text) => {
        if (stepOneList.children.length >= 3) {
            alert("Нельзя выбрать больше 3 направлений");
            return;
        }
        for (const t of stepOneList.children) {
            if (t.dataset.value == value) {
                alert("Данное направление уже выбрано");
                return;
            }
        }
        stepOneList.insertAdjacentHTML(
            "beforeend",
            `
    <li data-value=${value} ><strong>Нарпавление:</strong> ${text}</li>`
        );
        let newLi = document.querySelector('[data-value="' + value + '"]');

        let span = document.createElement("SPAN");
        let txt = document.createTextNode("\u00D7");
        span.className = "close";
        span.addEventListener("click", closes);
        span.appendChild(txt);
        newLi.appendChild(span);
        renderStep2(value, text);
    };
    const renderStep2 = (value, text) => {
        stepTwoList.insertAdjacentHTML(
            "beforeend",
            `
    <li data-priority=${value}><strong>Направление:</strong> <span class="direction" > ${text} </span></li>`
        );
        let newLi = document.querySelector('[data-priority="' + value + '"]');

        let spanUp = document.createElement("SPAN");
        //стрелка вверх
        let upArrow = document.createTextNode("\u25B2");
        spanUp.className = "upArrow";
        spanUp.addEventListener("click", toUp);
        spanUp.appendChild(upArrow);
        //стрелка вниз
        let spanDown = document.createElement("SPAN");
        let downArrow = document.createTextNode("\u25BC");
        spanDown.className = "downArrow";
        spanDown.addEventListener("click", toDown);
        spanDown.appendChild(downArrow);

        newLi.appendChild(spanUp);
        newLi.appendChild(spanDown);
    };



    IwindowButtons.forEach(function (item) {
        /* Назначаем каждой кнопке обработчик клика */
        item.addEventListener("click", function (e) {
            e.preventDefault();

            var IwindowId = this.getAttribute("data-Iwindow"),
                IwindowElem = document.querySelector(
                    '.Iwindow[data-Iwindow="' + IwindowId + '"]'
                );

            /* После того как нашли нужное модальное окно, добавим классы
                  подложке и окну чтобы показать их. */
            IwindowElem.classList.add("active");
            overlay.classList.add("active");
        }); // end click
    }); // end foreach

    closeButtons.forEach(function (item) {
        item.addEventListener("click", function (e) {
            var parentIwindow = this.closest(".Iwindow");

            parentIwindow.classList.remove("active");
            overlay.classList.remove("active");
        });
    }); // end foreach

    document.body.addEventListener(
        "keyup",
        function (e) {
            var key = e.keyCode;

            if (key == 27) {
                document.querySelector(".Iwindow.active").classList.remove("active");
                document.querySelector(".overlay").classList.remove("active");
            }
        },
        false
    );

    overlay.addEventListener("click", function () {
        document.querySelector(".Iwindow.active").classList.remove("active");
        this.classList.remove("active");
    });

    //кнопка формирования
    btnApplication.addEventListener("click", (e) => {



        if (stepTwoList.children.length < 1) {
            alert("Вы не добавили ни одного направления");
            return;
        }
        let i = 0;
        const obj = {};
        for (const value of stepTwoList.children) {
            let direction = value.dataset.priority;

            obj[++i] = direction;
        }
        function getCookie(c_name) {
            if(document.cookie.length > 0) {
                c_start = document.cookie.indexOf(c_name + "=");
                if(c_start != -1) {
                    c_start = c_start + c_name.length + 1;
                    c_end = document.cookie.indexOf(";", c_start);
                    if(c_end == -1) c_end = document.cookie.length;
                    return unescape(document.cookie.substring(c_start,c_end));
                }
            }
            return "";
        }
        console.log(JSON.stringify(obj));
        fetch("http://localhost:8081/app"+`/${username}`, {
            method: "POST",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
                "X-CSRFToken": getCookie("csrftoken"),
            },
            body: JSON.stringify(obj),
        })
            .then(function (res) {
                return res.json();
            })
            .then(function (data) {
                window.location.reload();
                console.log(JSON.stringify(data));
                //добавить
            });
        setTimeout(window.location.reload(),200);

    });



});

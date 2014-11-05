$(document).ready(function () {
    app.bind();
})

var app = {
    bind: function () {
        var filterField = $("#filter");
        filterField.on("input propertychange paste", function () {
            timer.start().done(function () {
                app.search(filterField.val());
            });
        });
        app.search();

        $("#login-form").on("submit", function () {
            remote.login($("#login_username").val(), $("#login_password").val());
            return false;
        });
    },
    search: function (text) {
        remote.search(text).done(function (result) {
            ui.showList(result);
        }).fail(function (ajax, state, errorMessage) {
            ui.showErrorDialog(errorMessage);
        });
    }
};

var ui = {
    showList: function (list) {
        $("#names").html("");
        $.each(list, function (index, item) {
            ui.buildEntry(list, item);
        });
    },
    buildEntry: function (list, item) {
        $("#names").append(ui.formatEntry(item));
    },
    formatEntry: function (item) {
        var src = $("#template").html()
            .replace("{lastName}", item.lastName)
            .replace("{firstName}", item.firstName);
        return $(src)
    },
    showErrorDialog: function (errorMessage) {
        var wrapper = $("#alert-wrapper");
        $("#message-status").html(errorMessage);
        wrapper.show();
        setTimeout(function () {
            wrapper.fadeOut();
        }, 3000);
    }
};

var timer = {
    delay: 500,
    currentTimer: null,
    cancelCurrent: function () {
        clearTimeout(timer.currentTimer);
    },
    start: function (delayOverride) {
        var delay = delayOverride || timer.delay;
        timer.cancelCurrent();
        var deferred = $.Deferred();
        timer.currentTimer = setTimeout(function () {
            deferred.resolve();
        }, delay);
        return deferred.promise();
    }
};

var remote = {
    search: function (text) {
        if (text) {
            return $.ajax("/AppSecDemo/demo/service/search/${q}".replace("${q}", text));
        }
        else {
            return $.ajax("/AppSecDemo/demo/service/entries/");
        }
    },
    login: function (username, password) {
        return $.ajax("/AppSecDemo/demo/service/login",
            {
                type: "POST",
                data: {
                    username: username,
                    password: password
                }
            }
        );
    }
};



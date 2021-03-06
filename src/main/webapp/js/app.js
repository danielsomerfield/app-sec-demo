$(document).ready(function () {
    app.bind();
    app.loadUserState();
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
            remote.login($("#login_username").val(), $("#login_password").val())
                .fail(function (ajax, state, errorMessage) {
                    ui.showErrorDialog("Login failed.");
                })
                .done(function (userState) {
                    $("#login_username").val("");
                    $("#login_password").val("");
                    ui.initAdminUI(userState["loggedIn"]);
                });
            return false;
        });

        $("#logout-form").on("submit", function () {
            remote.logout().done(function(){
                app.loadUserState();
            });
            return false;
        });

        $(".container").on("click", ".show-entry", function(){
            var id = $(this).attr("data-id");
            ui.showEntry(id);
        });

        $(".container").on("click", ".delete-entry", function(){
            var tag = $(this);
            var id = tag.attr("data-id");
            app.deleteEntry(id).success(function(){
                tag.parents(".entry-row").fadeOut();
            });
            return false;
        });
    },
    search: function (text) {
        remote.search(text).done(function (result) {
            ui.showList(result);
        }).fail(function (ajax, state, errorMessage) {
            ui.showErrorDialog(errorMessage);
        });
    },
    loadUserState: function () {
        remote.getUserState().success(function (userState) {
            ui.initAdminUI(userState["loggedIn"]);
        });
    },
    deleteEntry: function(id) {
        return remote.deleteEntry(id);
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
            .replace("{firstName}", item.firstName)
            .replace(/\{id\}/g, item.id);
        return $(src)
    },
    showErrorDialog: function (errorMessage) {
        var wrapper = $("#alert-wrapper");
        $("#message-status").html(errorMessage);
        wrapper.show();
        setTimeout(function () {
            wrapper.fadeOut();
        }, 3000);
    },
    initAdminUI: function (adminMode) {
        if (adminMode) {
            $(".admin-required").show();
            $("#login-panel").hide();
        }
        else {
            $("#login-panel").show();
            $(".admin-required").hide();
        }
    },
    showEntry: function(entryId) {
        $("#entry-frame").attr("src", "/AppSecDemo/demo/entry/?entryId=" + entryId);
        $("#entry-modal").modal("show");
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
    },
    deleteEntry: function(id) {
        return $.ajax("/AppSecDemo/demo/service/entries/${q}".replace("${q}", id),
            {
                type: "DELETE"
            }
        );
    },
    logout: function(){
        return $.ajax("/AppSecDemo/demo/service/logout",
            {
                type: "POST"
            }
        );
    },
    getUserState: function () {
        return $.ajax("/AppSecDemo/demo/service/userState");
    }
};



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
    },
    search: function(text){
        remote.search(text).done(function (result) {
            app.showList(result);
        }).fail(function (ajax, state, errorMessage) {
            app.showErrorDialog(errorMessage);
        });
    },
    showList: function (list) {
        console.log("Showing: " + JSON.stringify(list));
        $("#names").html("");
        $.each(list, function (index, item) {
            app.buildEntry(list, item);
        });
    },
    buildEntry: function (list, item) {
        $("#names").append(app.formatEntry(item));
    },
    formatEntry: function (item) {
        var src = $("#template").html()
            .replace("{lastName}", item.lastName)
            .replace("{firstName}", item.firstName);
        return $(src)
    },
    showErrorDialog : function(errorMessage)
    {
        var wrapper = $("#alert-wrapper");
        $("#message-status").html(errorMessage);
        wrapper.show();
        setTimeout(function(){
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
            //TODO: un-hardcode
            return $.ajax("http://localhost:8080/AppSecDemo/demo/service/search/${q}".replace("${q}", text));
        }
        else
        {
            return $.ajax("http://localhost:8080/AppSecDemo/demo/service/entries/");
        }
    }
};



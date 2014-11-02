$(document).ready(function () {
    app.bind();
})

var app = {
    bind: function () {
        var filterField = $("#filter");
        filterField.on("input propertychange paste", function () {
            timer.start().done(function () {
                remote.search(filterField.val()).done(function (result) {
                    app.showList(result);
                }).fail(function () {
                    //TODO: handle failure -- badly
                });
            });
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
    }
};

var timer = {
    delay: 500,
    currentTimer: null,
    cancelCurrent: function () {
        clearTimeout(timer.currentTimer);
    },
    start: function (fn) {
        timer.cancelCurrent();
        var deferred = $.Deferred();
        timer.currentTimer = setTimeout(function () {
            deferred.resolve();
        }, timer.delay);
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
            var d = $.Deferred();
            d.resolve(new Array());
            return d.promise();
        }
    }
};



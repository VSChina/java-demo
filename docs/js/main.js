$.ajax({
        method: "GET",
        url: "https://fabrikam-functions-java-demo.azurewebsites.net/api/hello?ua=" + window.navigator.userAgent,
    })
    .done(function () {
        $("#result").text("签到成功！");
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
        $("#result").text("签到失败！");
    });
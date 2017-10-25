$.ajax({
        method: "GET",
        url: "https://fabrikam-functions-java-demo.azurewebsites.net/api/register?ua=" + window.navigator.userAgent,
    })
    .done(function () {
        $("#result-text").text("签到成功");
        $("#result-img").attr("src", "images/sign_success@3x.png");
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
        $("#result-text").text("签到失败");
        $("#result-img").attr("src", "images/sign_fail@3x.png");
        $("#result-text").css("color", "#f55320");
        $("#try").show();
    })
    .always(function () {
        $("#result-img").css("height", "5%");
    })
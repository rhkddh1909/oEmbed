<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>OEMBED</title>
</head>
<link rel="stylesheet" href="/css/oembed.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript">
    function getOEmbed(url){
        const param={"strUrl" : $("#search").val()};
        $.ajax({
            url : "/oEmbed",
            type : "post",
            contentType : "application/json",
            data :  JSON.stringify(param),
            dataType : "json",
            success : function(result){
                if(result.status == "0000"){
                    $("#content").children().remove();
                    $("#content").append("<table style='width:1000px;'>");
                    $("#content").append("<div class='div_4'> " + "<span class='title'>"+"title"+"</span><span class='title'>" + result.rslt_DATA.title + "</span></div>");
                    let index = 0;
                    for(const [key,value] of Object.entries(result.rslt_DATA)){
                        if(key != "title"){
                            let convertValue = value;
                            let convertKey = key;
                            if(key.includes("url")){
                                 convertValue = "<a href="+ value + ">"+value+"</a>";
                            }
                            else if(key == "html"){
                                convertValue = result.rslt_DATA.html;
                                convertKey = "html<br/>(" + result.rslt_DATA.width + "/" + result.rslt_DATA.height + ")";
                            }
                            else if(key == "thumbnail_urlhttps"){
                                convertKey = "html<br/>(" + result.rslt_DATA.thumbnail_width + "/" + result.rslt_DATA.thumbnail_height + ")";
                            }

                            if(index%2 == 1){
                                if(key == "html"){
                                    $("#content").append("<div class='div_4'> " + "<span class='body'>"+convertKey+"</span><span class='body'><xmp>" + convertValue + "</xmp></span></div>");
                                    $("#content").append("<div class='div_4'> " + "<span class='body'></span><span class='body'>"+convertValue+"</span></div>");
                                }
                                else if(key == "thumbnail_url"){
                                    $("#content").append("<div class='div_4'> " + "<span class='body'>"+convertKey+"</span><span class='body'>"+convertValue+"</span></div>");
                                    $("#content").append("<div class='div_4'> " + "<span class='body'></span><span class='body'>"+ "<img src="+value+"></span></div>");
                                }
                                else{
                                    $("#content").append("<div class='div_4'> " + "<span class='body'>"+convertKey+"</span><span class='body'>" + convertValue + "</span></div>");
                                }
                            }
                            else{
                                if(key.includes("html")){
                                    $("#content").append("<div class='div_4 grey'> " + "<span class='body'>"+convertKey+"</span><span class='body'><xmp>" + convertValue + "</xmp></span></div>");
                                    $("#content").append("<div class='div_4 grey'> " + "<span class='body'></span><span class='body'>"+convertValue+"</span></div>");
                                }
                                else if(key == "thumbnail_url"){
                                    $("#content").append("<div class='div_4 grey'> " + "<span class='body'>"+convertKey+"</span><span class='body'>"+convertValue+"</span></div>");
                                    $("#content").append("<div class='div_4 grey'> " + "<span class='body'></span><span class='body'>"+ "<img src="+value+"></span></div>");
                                }
                                else{
                                    $("#content").append("<div class='div_4 grey'> " + "<span class='body'>"+convertKey+"</span><span class='body'>" + convertValue + "</span></div>");
                                }

                            }
                        }
                        index++;
                    }
                }
                else{
                    alert(result.rslt_MSG);
                }
            }
        });
    }
</script>
<script type="text/javascript">
    $(document).ready(function(){

    });
</script>
<body>
<div class="div_1">
    <div class="div_2">
        <div class="header">oEmbed Test</div>
        <div class="searchBar"><label>
            <input id="search" class="search" type="text"/>
            <input class="btn" type="button" value="확인" onclick="getOEmbed();"/>
        </label> </div>
    </div>
    <div class="div_3">
        <div id="content">
        </div>
    </div>
</div>
</body>
</html>
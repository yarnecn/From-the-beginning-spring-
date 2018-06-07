<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>
    <title>illusory</title>
    <style>
        body {
            margin: 0;
            overflow: hidden;
        }

        .background {
            width: 100vw;
            height: 100vh;
            background: #3E1E68;
        }

        .background span {
            width: 20vmin;
            height: 20vmin;
            border-radius: 20vmin;
            backface-visibility: hidden;
            position: absolute;
            animation-name: move;
            animation-duration: 6s;
            animation-timing-function: linear;
            animation-iteration-count: infinite;
        }
        .background span:nth-child(1) {
            color: #FFACAC;
            top: 44%;
            left: 97%;
            animation-duration: 15s;
            animation-delay: -13.8s;
            transform-origin: 11vw -22vh;
            box-shadow: 40vmin 0 10.12735945vmin currentColor;
        }
        .background span:nth-child(2) {
            color: #E45A84;
            top: 93%;
            left: 8%;
            animation-duration: 13.8s;
            animation-delay: -10.9s;
            transform-origin: 10vw 23vh;
            box-shadow: 40vmin 0 12.7346672971vmin currentColor;
        }
        .background span:nth-child(3) {
            color: #FFACAC;
            top: 14%;
            left: 41%;
            animation-duration: 15.9s;
            animation-delay: -6.7s;
            transform-origin: -9vw -24vh;
            box-shadow: -40vmin 0 11.4923952406vmin currentColor;
        }
        .background span:nth-child(4) {
            color: #E45A84;
            top: 52%;
            left: 75%;
            animation-duration: 14.3s;
            animation-delay: -12.6s;
            transform-origin: 16vw 25vh;
            box-shadow: 40vmin 0 13.3657534039vmin currentColor;
        }
        .background span:nth-child(5) {
            color: #FFACAC;
            top: 7%;
            left: 5%;
            animation-duration: 15.9s;
            animation-delay: -10s;
            transform-origin: 8vw 3vh;
            box-shadow: -40vmin 0 13.3942178273vmin currentColor;
        }
        .background span:nth-child(6) {
            color: #FFACAC;
            top: 35%;
            left: 1%;
            animation-duration: 11.6s;
            animation-delay: -6s;
            transform-origin: 0vw 17vh;
            box-shadow: -40vmin 0 7.6304875279vmin currentColor;
        }
        .background span:nth-child(7) {
            color: #FFACAC;
            top: 42%;
            left: 84%;
            animation-duration: 11s;
            animation-delay: -6.9s;
            transform-origin: 11vw -8vh;
            box-shadow: 40vmin 0 13.6665321499vmin currentColor;
        }
        .background span:nth-child(8) {
            color: #E45A84;
            top: 53%;
            left: 95%;
            animation-duration: 15s;
            animation-delay: -10.9s;
            transform-origin: 25vw 15vh;
            box-shadow: 40vmin 0 13.2849881717vmin currentColor;
        }
        .background span:nth-child(9) {
            color: #FFACAC;
            top: 75%;
            left: 67%;
            animation-duration: 11.7s;
            animation-delay: -13.9s;
            transform-origin: -11vw -3vh;
            box-shadow: 40vmin 0 7.2158804055vmin currentColor;
        }
        .background span:nth-child(10) {
            color: #583C87;
            top: 59%;
            left: 51%;
            animation-duration: 12.1s;
            animation-delay: -11.7s;
            transform-origin: -5vw -7vh;
            box-shadow: -40vmin 0 7.4269772253vmin currentColor;
        }
        .background span:nth-child(11) {
            color: #FFACAC;
            top: 80%;
            left: 91%;
            animation-duration: 13.4s;
            animation-delay: -5.9s;
            transform-origin: 7vw -19vh;
            box-shadow: 40vmin 0 11.2741559637vmin currentColor;
        }
        .background span:nth-child(12) {
            color: #FFACAC;
            top: 68%;
            left: 97%;
            animation-duration: 15.8s;
            animation-delay: -8.2s;
            transform-origin: 24vw 3vh;
            box-shadow: 40vmin 0 11.2552482722vmin currentColor;
        }
        .background span:nth-child(13) {
            color: #FFACAC;
            top: 69%;
            left: 46%;
            animation-duration: 10.4s;
            animation-delay: -11.5s;
            transform-origin: 13vw 24vh;
            box-shadow: -40vmin 0 6.2939412125vmin currentColor;
        }
        .background span:nth-child(14) {
            color: #E45A84;
            top: 98%;
            left: 63%;
            animation-duration: 14.2s;
            animation-delay: -3s;
            transform-origin: -13vw -15vh;
            box-shadow: -40vmin 0 10.5039271244vmin currentColor;
        }
        .background span:nth-child(15) {
            color: #583C87;
            top: 12%;
            left: 20%;
            animation-duration: 14s;
            animation-delay: -6.8s;
            transform-origin: 7vw 20vh;
            box-shadow: 40vmin 0 10.924056322vmin currentColor;
        }
        .background span:nth-child(16) {
            color: #E45A84;
            top: 29%;
            left: 20%;
            animation-duration: 11s;
            animation-delay: -14.4s;
            transform-origin: -20vw 4vh;
            box-shadow: -40vmin 0 13.3859956789vmin currentColor;
        }
        .background span:nth-child(17) {
            color: #FFACAC;
            top: 88%;
            left: 87%;
            animation-duration: 10.7s;
            animation-delay: -3.7s;
            transform-origin: -11vw 12vh;
            box-shadow: 40vmin 0 14.8726292689vmin currentColor;
        }
        .background span:nth-child(18) {
            color: #583C87;
            top: 57%;
            left: 35%;
            animation-duration: 10.1s;
            animation-delay: -13.6s;
            transform-origin: -19vw -1vh;
            box-shadow: 40vmin 0 8.5771411832vmin currentColor;
        }
        .background span:nth-child(19) {
            color: #583C87;
            top: 11%;
            left: 37%;
            animation-duration: 11.5s;
            animation-delay: -10.1s;
            transform-origin: -20vw 22vh;
            box-shadow: 40vmin 0 8.1729042124vmin currentColor;
        }
        .background span:nth-child(20) {
            color: #583C87;
            top: 53%;
            left: 60%;
            animation-duration: 12.3s;
            animation-delay: -11.3s;
            transform-origin: 10vw -1vh;
            box-shadow: 40vmin 0 13.5347652223vmin currentColor;
        }

        @keyframes move {
            100% {
                transform: translate3d(0, 0, 1px) rotate(360deg);
            }
        }
    </style>
</head>
<body>
<div class="background">
    <span></span>
    <span></span>
    <span></span>
    <span></span>
    <span></span>
    <span></span>
    <span></span>
    <span></span>
    <span></span>
    <span></span>
    <span></span>
    <span></span>
    <span></span>
    <span></span>
    <span></span>
    <span></span>
    <span></span>
    <span></span>
    <span></span>
    <span></span>
</div>
<h2>恭喜登录成功</h2>
</body>
</html>
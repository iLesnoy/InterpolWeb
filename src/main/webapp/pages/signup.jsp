<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

<style>
    .form-group{
        margin-bottom: 15px;
    }
    label{
        margin-bottom: 15px;
    }
    input,
    input::-webkit-input-placeholder {
        font-size: 11px;
        padding-top: 3px;
    }
    .form-control {
        height: auto!important;
        padding: 8px 12px !important;
    }
    .input-group {
        box-shadow: 0px 2px 5px 0px rgba(0,0,0,0.21)!important;
    }
    #button {
        border: 1px solid #ab3d3d;
        margin-top: 28px;
        padding: 6px 12px;
        color: #666;
        text-shadow: 0 1px #fff;
        cursor: pointer;
        border-radius: 3px 3px;
        box-shadow: 0 1px #fff inset, 0 1px #ddd;
        background: #f5f5f5;
        background: -moz-linear-gradient(top, #f5f5f5 0%, #8d1717 100%);
        background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #f5f5f5), color-stop(100%, #eeeeee));
        background: -webkit-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
        background: -o-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
        background: -ms-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
        background: linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
        filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f5f5f5', endColorstr='#eeeeee', GradientType=0);
    }
    .main-form{
        margin-top: 30px;
        margin: 0 auto;
        max-width: 400px;
        padding: 10px 40px;
        background: #eec84b;
        color: #FFF;
        text-shadow: none;
        box-shadow: 0px 3px 5px 0px rgba(0,0,0,0.31);
    }
    span.input-group-addon i {
        color: #ecd863;
        font-size: 17px;
    }
    .login-button{
        margin-top: 5px;
    }
</style>

<div class="container">
    <div class="row main-form">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="sign_up">

            <div class="form-group">
                <label for="email" class="cols-sm-2 control-label">Your Email</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="email" id="email" placeholder="Enter your Email"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="password" class="cols-sm-2 control-label">Password</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <input type="password" class="form-control" name="password" id="password" placeholder="Enter your Password"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="confirm" class="cols-sm-2 control-label">Confirm Password</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <input type="password" class="form-control" name="confirm_password" id="confirm" placeholder="Confirm your Password"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="name" class="cols-sm-2 control-label">Your Name</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="name" id="name" placeholder="Enter your Name"/>
                    </div>
                </div>
            </div>



            <div class="form-group">
                <label for="surname" class="cols-sm-2 control-label">Your Surname</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="surname" id="surname" placeholder="Enter your Username"/>
                    </div>
                </div>
            </div>

            <div class="form-group ">
                <input type="submit" class="btn btn-primary btn-lg btn-block login-button" value="submit"/>
            </div>
        </form>
    </div>
</div>



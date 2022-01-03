<link rel="stylesheet" href="https://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css" />
<link rel="stylesheet" href="https://bootstraptema.ru/plugins/font-awesome/4-4-0/font-awesome.min.css" />
<link rel="stylesheet" href="https://bootstraptema.ru/snippets/font/2016/pe-icon-stroke/Pe-icon-7-stroke.css">
<link href="https://bootstraptema.ru/_sf/3/391.css" rel="stylesheet"/>
<script src="https://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
<script src="https://bootstraptema.ru/plugins/2015/b-v3-3-6/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/masonry/3.3.1/masonry.pkgd.min.js"></script>

<div class="wrapper">

    <div class="container">

        <div class="masonry-container">


            <div class="card-box col-md-4 col-sm-6">
                <div class="card card-with-border card-just-text" data-background="color" data-color="black" >
                    <div class="content">
                        <h4 class="title">"In the end we only <b>regret</b> the chances we didn't take."</h4>
                        <p class="description">- Hipster Quote</p>
                    </div>
                </div> <!-- end card -->
            </div>



            <div class="card-box col-md-4 col-sm-6">
                <div class="card">
                    <div class="header">
                        <img src="https://bootstraptema.ru/snippets/block/2016/style-blocks/lifestyle-8.jpg"/>
                        <div class="filter"></div>

                        <div class="actions">
                            <button class="btn btn-round btn-fill btn-neutral btn-modern">
                                Read Article
                            </button>
                        </div>
                    </div>

                    <div class="content">
                        <h6 class="category">News</h6>
                        <h4 class="title"><a href="#">Try the new hairstyle from this Barber Shop </a></h4>
                        <p class="description">When selling products it's always a good idea to go with commanding fonts that are good at showing authority and security. I feel like Raleway and Roboto do just that.</p>
                    </div>
                </div> <!-- end card -->
            </div>
        </div>
    </div>
</div>




<script>
    $(document).ready(function(){
        window_width = $(window).width();

        // Make the images from the card fill the hole space
        hipster_cards.fitBackgroundForCards();

    });

    hipster_cards = {
        misc:{
            navbar_menu_visible: 0
        },

        fitBackgroundForCards: function(){
            $('[data-background="image"]').each(function(){
                $this = $(this);

                background_src = $this.data("src");

                if(background_src != "undefined"){
                    new_css = {
                        "background-image": "url('" + background_src + "')",
                        "background-position": "center center",
                        "background-size": "cover"
                    };

                    $this.css(new_css);
                }
            });

            $('.card .header img').each(function(){
                $card = $(this).parent().parent();
                $header = $(this).parent();

                background_src = $(this).attr("src");

                if(background_src != "undefined"){
                    new_css = {
                        "background-image": "url('" + background_src + "')",
                        "background-position": "center center",
                        "background-size": "cover"
                    };

                    $header.css(new_css);
                }
            });

        },
    }
</script>

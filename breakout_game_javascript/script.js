var canvas = document.getElementById('myCanvas');
var ctx = canvas.getContext('2d');

let x = canvas.width/2;
let y = canvas.height - 30;
let dx = 3;
let dy = -3;
const ballRadius = 20;
let paddleHeight = 10;
let paddleWidth = 75;
let paddleX = (canvas.width-paddleWidth)/2;
let rightPressed = false;
let leftPressed = false;
let brickRowCount = 3;
const brickColumnCount = 5;
const brickWidth = 75;
const brickHeight = 20;
const brickPadding = 10;
const brickOffsetTop = 30;
const brickOffsetLeft = 30;
let score = 0;
let lives = 3;
let level = 1;
let maxLevel = 5;
let ball = new Image();
ball.src = 'http://pngimg.com/uploads/football/football_PNG52790.png'

let bricks = [];
initBricks();
function initBricks() {
	for (c=0; c<brickColumnCount; c++) {
		bricks[c] = [];
		for (r=0; r<brickRowCount; r++) {
			bricks[c][r] = {x: 0, y:0, status: 1};
		}
	}
}

document.addEventListener("keydown", keyDownHandler);
document.addEventListener("keyup", keyUpHandler);

function keyDownHandler(e) {
    if(e.keyCode == 39) {
        rightPressed = true;
    } else if (e.keyCode == 37) {
        leftPressed = true;
    }
}

function keyUpHandler(e) {
    if(e.keyCode == 39) {
        rightPressed = false;
    } else if (e.keyCode == 37) {
        leftPressed = false;
    }
}

function drawBall() {
    ctx.drawImage(ball, x, y, ballRadius, ballRadius);
}

function drawPaddle() {
	ctx.beginPath();
	ctx.rect(paddleX, canvas.height-paddleHeight, paddleWidth, paddleHeight);
	ctx.fillStyle = "blue";
	ctx.fill();
	ctx.closePath();
}

function collisionDetection() {
	for(c=0; c<brickColumnCount; c++){
		for(r=0; r<brickRowCount; r++){
			var b = bricks[c][r];
			if(b.status  == 1) {
				if(x > b.x && x < b.x+brickWidth && y > b.y && y < b.y+brickHeight) {
					dy = -dy;
					b.status = 0;
                    score++;
					if(score == brickRowCount*brickColumnCount) {
						if(level === maxLevel) {
							alert("YOU WIN, CONGRADULATIONS!");
							document.location.reload();
						} else {
							level++;
							brickRowCount++;
							initBricks();
							score=0;
							if(dx>=0)
								dx += 1;
							else dx -= 1
							if(dy>=0)
								dy += 1;
							else dy -= 1;
							x = canvas.width/2;
							y = canvas.height-30;
							paddleX = (canvas.width-paddleWidth)/2;
						}
					}
				}
			}
		}
	}
}

function drawBricks() {
	for(c=0; c<brickColumnCount; c++) {
		for(r=0; r<brickRowCount; r++) {
			if(bricks[c][r].status == 1) {
				var brickX = (c*(brickWidth+brickPadding))+brickOffsetLeft;
				var brickY = (r*(brickHeight+brickPadding))+brickOffsetTop;
				bricks[c][r].x = brickX;
				bricks[c][r].y = brickY;
				ctx.beginPath();
				ctx.rect(brickX, brickY, brickWidth, brickHeight);
				ctx.fillStyle = "blue";
				ctx.fill();
				ctx.closePath();
			}
		}
	}
}

function drawScore() {
    ctx.font = "16px Arial";
    ctx.fillStyle = "blue";
    ctx.fillText("Score: " + score, 8, 20)
}

function drawLives() {
    ctx.font = "16px Arial";
    ctx.fillStyle = "red";
    ctx.fillText("Lives: " + lives, canvas.width - 65, 20)
}

function drawLevel() {
    ctx.font = "16px Arial";
    ctx.fillStyle = "green";
    ctx.fillText("Level: " + level, 210, 20)
}

function draw() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawBall();
    drawPaddle();
    drawBricks();
    collisionDetection();
    drawScore();
	drawLives();
	drawLevel();

    if(y + dy < ballRadius) {
        dy = -dy;
    } else if (y + dy + ballRadius > canvas.height) {
        if(x > paddleX && paddleX + paddleWidth > x){
            dy = -dy
        } else {
			lives--;
			if(!lives){
				alert("GAME OVER");
				document.location.reload();
				dy = -dy;
			} else {
				x = canvas.width/2;
				y = canvas.height-30;
				paddleX = (canvas.width-paddleWidth)/2;
			}
        }
    }
    if(x + dx < ballRadius || x + dx + ballRadius > canvas.width) {
        dx = -dx;
    }

    if(rightPressed && paddleX < canvas.width-paddleWidth) {
		paddleX += 7;
	}
	else if(leftPressed && paddleX > 0) {
		paddleX -= 7;
	}

    x += dx;
    y += dy;
	requestAnimationFrame(draw);
}

document.addEventListener("mousemove", mouseMoveHandler);

function mouseMoveHandler(e) {
	var relativeX = e.clientX - canvas.offsetLeft;
	if(relativeX > 0+paddleWidth/2 && relativeX < canvas.width-paddleWidth/2) {
		paddleX = relativeX - paddleWidth/2;
	}
}

//setInterval(draw, 10);
draw()

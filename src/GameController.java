
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**GameController_JPanel.java
 * This class formats the game interface
 * @author Jessica Hu (5975122)
 */


public class GameController extends JPanel
{
	private static final long serialVersionUID = 7147716465441279160L; ///
	
	//-------------------------------------Attributes----------------------------------------------------------
	private WindowJFrame window;
	private JButton menu_button = new JButton();
	private Dimension game_field = new Dimension(800,560); //game background
	private ImageIcon background; //background
	private JLabel background_label; //background container
	
	
	//Game components
	private int lives;
	private int score;
	private Ball ball;
	private Paddle paddle;
	private BrickGroup brick_group;
	private PowerUp power;
	
	//Game states
	Thread ball_thread;
	private boolean game_running;
	private boolean ball_touch_ground;
	private boolean finished_level;
	private boolean start_new_level;
	private boolean lost_life;
	private boolean dropping_powerup;
	private boolean game_paused;
	
	//Game Outcomes
	private boolean game_won;
	private boolean game_lost;
	private Label lives_text = new Label (); //display lives
	private Label score_text = new Label (); //display score
	
	private int game_difficulty=3; // set the game difficulty
	private int current_game_difficulty=3; //set current game difficulty
	private int total_levels =3; //set number of levels to win (MUST BE LESS OR EQUAL TO 5)
	private int current_level = 1; //set current level
	
	private boolean super_ball=false;
	private boolean bombed=false;
	
	//---------------------------------------Constructor--------------------------------------------------------
	//***Create a New Game************************************************************************************
	public GameController(WindowJFrame w)
	{
		System.out.println("@GameController"+'\t' + "Creating Card 3: GameController...");
		window = w;
		setSize(790,560);//set the size of this game controller
		initialiseWindowComponents();// Create and add a menu button
		createPaddleBallBricksPower();// Create game components
	}
	
	
	//----------------------------------General Methods---------------------------------------------------------
	
	//***Set-a Get-a Methods************************************************************************************
	public int getLives(){return lives;}
	public int getScore(){return score;}
	public void setLives(int l){lives=l;}
	public void incrementScore(int s){score+=s;} //increment score by specified value
	public int getGameFieldWidth(){return game_field.width;} // return the width of the playing area
	public int getGameFieldHeight(){return game_field.height;} // return the height of the playing area
	
	public Paddle getPaddle(){return paddle;}
	public BrickGroup getBrickGroup(){return brick_group;}
	
	//public void dropPowerUp(Rectangle r){power_up=new PowerUp(this,r);} //pass the position and size of the brick to "power up"
	public boolean isGamePaused(){return game_paused;}
	public boolean isGameRunning(){return game_running;}
	
	public boolean getGameOutcome(){
		if(game_won==true) {return true;}else{return false;}
	}
	
	public void setGameWon(){game_won=true;game_lost=false;}
	public void setGameLost(){game_lost=true;game_won=false;}
	
	public void setGameDifficulty(int d){game_difficulty=d;brick_group.setGameDifficulty(d);}
	
	public void setDropDownBoolean(boolean b){dropping_powerup=b;} // give other classes the power to change whether the 
	public void createPowerUp(Rectangle r){power.dropPowerUp(r);}//calls power up to determine whether a powerup will be created
	
	public void implementLongPaddle(){paddle.makeLong();}//make the paddle long
	public void implementSuper(){super_ball=true;ball.makeSuper();}
	public void implementBomb(){groundBall();bombed=true;}
	
	public int getPaddleXPosition(){return paddle.getXPosion();}
	
	
	//***Create and format components on JPanel********************************************************************************
	public void initialiseWindowComponents()
	{
		// Format text
		menu_button.setText("Menu");
		lives_text.setText("Lives");
		score_text.setText("Score");
		
		// Format background
		background = new ImageIcon(getClass().getResource("Card3_Play.jpg"));
		background_label = new JLabel(background);
		
		
		try{ 
			menu_button.setFont(new Font ("Bradley Hand ITC",Font.BOLD, 24));
		}catch(Exception e){System.out.println("Error Setting Font (@MenuJPanel)");}// Continues the game even if the font cannot be set
		
		
		// Format button
		menu_button.setOpaque(false);
		menu_button.setContentAreaFilled(false);
		menu_button.setBorderPainted(false);
		
		//level_back_label.setOpaque(true);
		
		// button action
		initialiseButtonAction();
		
		// Add components to the JPanel
		add(menu_button);
		add(background_label);
		
	}
	
	//***Initialise button press action**************************************************************************
	public void initialiseButtonAction()
	{
		menu_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				gameStop();
				System.out.println("@GameController"+'\t' + "Ending Game...");
				System.out.println(" "+'\t'+'\t' + "Lives:"+lives+ " Score:"+score );
				window.switchCard(2); //switch to card 3: play screen
			}
		});
	}
	
	//***Create Paddle, Ball and Brick********************************************************************************
	public void createPaddleBallBricksPower()
	{
		ball = new Ball (this);
		paddle = new Paddle(this);
		brick_group = new BrickGroup(this);
		power = new PowerUp(this);
	}
	
	//***Initialise the game******************************************************************************************
	public void initialiseGameStates(){ //run everytime the card layout is switched to this card
		lives = 3;
		score = 0;
		
		game_running=false;
		ball_touch_ground=false;
		finished_level=false;
		game_paused=false;
		game_won=false;
		game_lost=false;
		
		current_game_difficulty=game_difficulty;
		current_level=1;
		start_new_level=true;
		lost_life=false;
		dropping_powerup=false;
		super_ball=false;
		
	}
	
	//***Initialize Positions of Ball and Paddle****************************************************************
	public void initialiseBallPaddle()
	{
		System.out.println("@GameController"+'\t' + "Formating Paddle & Ball...");
		generateRandomBallStart();
		ball.setPosition((int)game_field.width/2+ball.getBallRadius(), 510);//initialize ball position
		paddle.setPosition((int)(game_field.width-paddle.getWidth())/2, (int) (game_field.height-paddle.getHeight()-18));//start with the paddle in the middle
		ball.makeNormal();
		paddle.initialisePaddleAttributes();
		brick_group.resetBrick();
		power.initialisePowerUp();
	}
	
	public void initialiseBrick(){brick_group.generateBrickArrangement();}
	
	public void reinitialiseGameAttributes(){
		brick_group.setGameDifficulty(current_game_difficulty);
		initialiseBallPaddle();
		initialiseBrick();
		start_new_level=true;
		dropping_powerup=false;
		super_ball=false;
		paddle.makeNormal();
	}
	
	
	//----------------------------------Action Methods---------------------------------------------------------
	
	//***Action: Finished Level*********************************************************************************
	public void finishedLevel(){
		if(current_level<total_levels){//game still going
			System.out.println("@GameController"+'\t' + "Change level...");
			finished_level=true;
			current_level++;
			current_game_difficulty++;
			reinitialiseGameAttributes();
			gameThread();
			repaint();
		}else{//game won
			setGameWon();
			gameStop();
			current_game_difficulty=game_difficulty;
		}
	}
	
	//***Action: Ball Hits Ground*******************************************************************************
	public void groundBall()
	{
		lives--;
		if(lives>0){//keep playing
			//Reset position of the ball and paddle
			generateRandomBallStart();
			ball.setPosition((int)game_field.width/2+ball.getBallRadius(), 510);//initialize ball position
			paddle.setPosition((int)(game_field.width-paddle.getWidth())/2, (int) (game_field.height-paddle.getHeight()-18));//start with the paddle in the middle
			dropping_powerup=false;
			super_ball=false;
			lost_life=true;
			paddle.makeNormal();
			repaint();
			gameBallTouchGround();
		}else{// lose game if player run out of lives
			setGameLost();
			gameStop();
			
		}
	}
	
	public void brickTouchGround()
	{
		lives--;
		if(lives>0){//keep playing
			//Reset position of the ball and paddle
			
			reinitialiseGameAttributes();
			generateRandomBallStart();
			ball.setPosition((int)game_field.width/2+ball.getBallRadius(), 510);//initialize ball position
			paddle.setPosition((int)(game_field.width-paddle.getWidth())/2, (int) (game_field.height-paddle.getHeight()-18));//start with the paddle in the middle
			dropping_powerup=false;
			super_ball=false;
			lost_life=true;
			ball_touch_ground=true;
			paddle.makeNormal();
			gameThread();
			repaint();
		}else{// lose game if player run out of lives
			setGameLost();
			gameStop();
			
		}
	}
	
	//***Action: Move Paddle and Ball***************************************************************************
	public void movePaddle(boolean b){paddle.moveXDirection(b);}
	public void moveBall(){ball.movementLogic();}
	public void moveBricks(){brick_group.movementLogic();}
	public void movePowerUp(){power.movementLogic();}
	public void ballDirection(int x_movement,int y_movement){ball.setMovement(x_movement,y_movement);}
	
	
	//***Action: Generate random ball start position************************************************************
	public void generateRandomBallStart(){
		ball.setMovement(10,-10);
	}
	
	//***Action: Defines Keyboard-Press Action******************************************************************
	public void enableKeyboardControl()
	{
		this.requestFocus();
		this.addKeyListener(new KeyAdapter()//can also use keyListener
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode()==KeyEvent.VK_RIGHT)movePaddle(true);//Move right
				if (e.getKeyCode()==KeyEvent.VK_LEFT)movePaddle(false);//Move left
				if (e.getKeyCode()==KeyEvent.VK_P) {if(!game_paused){game_paused=true; repaint();}else{game_paused=false;}	}//Pause Game
				
				//Start the game if the game has not started and space is pressed.
				
				if (e.getKeyCode()==KeyEvent.VK_SPACE){
					if(!game_running||ball_touch_ground||finished_level||super_ball){gameStart();}
				}
			}
		});
	}
	
	//---------------------------------Runnable Method---------------------------------------------------------

	//***play the game******************************************************************************************
	public void playGame()
	{
		System.out.println("@GameController"+'\t' + "Starting Game...");
		
		initialiseGameStates(); //set everything to false
		reinitialiseGameAttributes(); //set everything to normal. Disable all powerups
		
		// Create game
		gameThread();
	}
	
	//create the game thread
	public void gameThread(){
		ball_thread = new Thread(new Runnable() 
		{
			public void run()
			{
				game_running = true;
				//ballDirection(10,-10); // move to the right by 10 and up by 10 every 50ms
				while(game_running) // when the game is running, ball is moving.
				{
					if(!game_paused){
						if(!ball_touch_ground){
							if(!finished_level){
								moveBall();	
								moveBricks();
								if(dropping_powerup){
									movePowerUp();
								}
								repaint();
								try{Thread.sleep(50);}catch(Exception e){}
							}
						}
					}
					if(lives<=0)game_running=false;
				}
			}
		});
		// Create game components
		initialiseBallPaddle();
		initialiseBrick();
		enableKeyboardControl();
	}
	
	
	//***Thread of the ball moving******************************************************************************
		public void gameStart(){
			ball_touch_ground= false;finished_level=false;lost_life=false;start_new_level=false;super_ball=false;bombed=false;
			ball.makeNormal();dropping_powerup=false;
			if(!game_running){ball_thread.start();}
		}
		public void gameBallTouchGround(){ball_touch_ground = true;}
		public void gameStop(){if(game_running){game_running = false;}window.switchCard(4);}//switch to card 4: end menu screen
	
	
	//***Graphics: Game Border**********************************************************************************
	public void paint(Graphics g)
	{
		
		super.paint(g); // paint itself
		g.translate((getWidth()-game_field.width)/2, (getHeight()-game_field.height)/2+15);
		
		brick_group.render(g); //render bricks
		paddle.render(g); //calls Paddle.java to render it
		ball.render(g); //calls Ball.java to render it
		if(dropping_powerup){power.render(g);} //render powerup if we are dropping it. visibility is set in PowerUp.java
		
		g.setFont(new Font ("Bradley Hand ITC",Font.BOLD, 24));	//set font
		g.drawString("Life: "+lives, window.getWidth()*12/32, window.getHeight()*12/600);//display the lifes
		g.drawString("Score: " +score, window.getWidth()*20/32, window.getHeight()*12/600);//display the score
		
		
		background_label.setLocation(window.getWidth()/2-background_label.getWidth()/2, window.getHeight()/2-background_label.getHeight()/2);
		menu_button.setLocation(window.getWidth()-menu_button.getWidth(), window.getHeight()*25/600-menu_button.getHeight()/2); // move the button
		
		
		
		
		// linked if statements to give precidence to the screens
		if(lost_life){
			if(bombed){
				g.setColor(new Color(255,120,0,200));
				g.fillRoundRect(window.getWidth()*3/16, window.getWidth()/8, window.getWidth()*5/8, window.getHeight()/2, 20, 20);
				
				g.setColor(new Color(255,255,255));
				g.drawRoundRect(window.getWidth()*3/16, window.getWidth()/8, window.getWidth()*5/8, window.getHeight()/2, 20, 20);
				
				g.setFont(new Font ("Berlin Sans FB Demi",Font.BOLD+Font.ITALIC, 100));
				g.drawString("Bombed!", window.getWidth()*13/64, window.getHeight()*7/16);
				
				g.setFont(new Font ("Bradley Hand ITC",Font.BOLD+Font.ITALIC, 24));
				g.drawString("Press SPACE to start.", window.getWidth()*11/32, window.getHeight()*9/16);
			}else{
				g.setColor(new Color(255,120,0,200));
				g.fillRoundRect(window.getWidth()*3/16, window.getWidth()/8, window.getWidth()*5/8, window.getHeight()/2, 20, 20);
				
				g.setColor(new Color(255,255,255));
				g.drawRoundRect(window.getWidth()*3/16, window.getWidth()/8, window.getWidth()*5/8, window.getHeight()/2, 20, 20);
				
				g.setFont(new Font ("Berlin Sans FB Demi",Font.BOLD+Font.ITALIC, 100));
				g.drawString("Lost a Life", window.getWidth()*13/64, window.getHeight()*7/16);
				
				g.setFont(new Font ("Bradley Hand ITC",Font.BOLD+Font.ITALIC, 24));
				g.drawString("Press SPACE to start.", window.getWidth()*11/32, window.getHeight()*9/16);
			}
		}else if(start_new_level){
			g.setColor(new Color(255,120,0,200));
			g.fillRoundRect(window.getWidth()*3/16, window.getWidth()/8, window.getWidth()*5/8, window.getHeight()/2, 20, 20);
			
			g.setColor(new Color(255,255,255));
			g.drawRoundRect(window.getWidth()*3/16, window.getWidth()/8, window.getWidth()*5/8, window.getHeight()/2, 20, 20);
			
			g.setFont(new Font ("Berlin Sans FB Demi",Font.BOLD+Font.ITALIC, 120));
			g.drawString("Level "+current_level, window.getWidth()*8/32, window.getHeight()*7/16);
			
			g.setFont(new Font ("Bradley Hand ITC",Font.BOLD+Font.ITALIC, 24));
			g.drawString("Press SPACE to start.", window.getWidth()*11/32, window.getHeight()*9/16);
		}else if(game_paused){
			g.setColor(new Color(255,120,0,200));
			g.fillRoundRect(window.getWidth()*3/16, window.getWidth()/8, window.getWidth()*5/8, window.getHeight()/2, 20, 20);
			
			g.setColor(new Color(255,255,255));
			g.drawRoundRect(window.getWidth()*3/16, window.getWidth()/8, window.getWidth()*5/8, window.getHeight()/2, 20, 20);
			
			g.setFont(new Font ("Berlin Sans FB Demi",Font.BOLD+Font.ITALIC, 100));
			g.drawString("Paused", window.getWidth()*9/32, window.getHeight()*7/16);
			
			g.setFont(new Font ("Bradley Hand ITC",Font.BOLD+Font.ITALIC, 24));
			g.drawString("Press P to restart.", window.getWidth()*11/32, window.getHeight()*9/16);
		}
		
		
	}
}